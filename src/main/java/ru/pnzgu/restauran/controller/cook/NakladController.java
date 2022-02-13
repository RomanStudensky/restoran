package ru.pnzgu.restauran.controller.cook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.*;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.service.*;

import java.util.List;

@Controller
@RequestMapping("/cook/naklad")
@RequiredArgsConstructor
public class NakladController {

    private final String REDIRECT_URL = "redirect:/cook/naklad/%s";
    private final String COMMON_VIEW =  "/cook/naklad/commonView";
    private final String CREATE_NAKLAD_VIEW = "/cook/naklad/action/naklad/create";
    private final String UPDATE_NAKLAD_VIEW = "/cook/naklad/action/naklad/update";
    private final String CREATE_SOSTAV_POSTAV_VIEW = "/cook/naklad/action/sostav/create";

    private final NakladService nakladService;
    private final SostavPostavService sostavPostavService;
    private final DogovorService dogovorService;
    private final PostavshikService postavshikService;
    private final ProductService productService;


    @GetMapping()
    public String getCommonView(Model model) {
        model.addAttribute("nakladList", nakladService.getAll());

        return COMMON_VIEW;
    }

    @GetMapping("/{nakladId}")
    public String getCommonView(@PathVariable Long nakladId, Model model) {
        try {
            nakladService.get(nakladId);
        } catch (NotFoundException e) {
            return "redirect:/director/naklad";
        }
        model.addAttribute("nakladList", nakladService.getAll());

        List<SostavPostavDTO> sostavList = sostavPostavService.getAllSostavBySostavNaklId(nakladId);
        model.addAttribute("sostavList", sostavList);
        model.addAttribute("nakladId", nakladId);

        return COMMON_VIEW;
    }

    @GetMapping("/create/view")
    public String getNakladCreateView(Model model) {
        model.addAttribute("naklad", new NakladDTO());
        model.addAttribute("postavshikList", postavshikService.getAll());
        model.addAttribute("dogovorList", dogovorService.getAll());
        model.addAttribute("postavshikDto", new PostavshikDTO());
        model.addAttribute("dogovorDto", new DogovorDTO());

        return CREATE_NAKLAD_VIEW;
    }

    @GetMapping("/update/view/{id}")
    public String getNakladUpdateView(@PathVariable Long id, Model model) {
        NakladDTO nakladDTO = nakladService.get(id);
        model.addAttribute("naklad", nakladDTO);
        model.addAttribute("postavshikList", postavshikService.getAll());
        model.addAttribute("dogovorList", dogovorService.getAll());
        model.addAttribute("postavshikDto", nakladDTO.getPostavshik());
        model.addAttribute("dogovorDto", nakladDTO.getDogovor());

        return UPDATE_NAKLAD_VIEW;
    }

    @GetMapping("/sostav/create/view/{nakladId}")
    public String getSostavCreateView(@PathVariable Long nakladId, Model model) {
        model.addAttribute("sostav", new SostavPostavDTO());
        model.addAttribute("nakladId", nakladId);
        model.addAttribute("productDto", new ProductDTO());
        model.addAttribute("productList", productService.getAll());

        return CREATE_SOSTAV_POSTAV_VIEW;
    }

    // ------------- REST ------------

    @PostMapping("/create")
    public String createNaklad(@ModelAttribute(name = "naklad") NakladDTO naklad,
                               @ModelAttribute(name = "dogovorDto") DogovorDTO dogovorDTO,
                               @ModelAttribute(name = "postavshikDto") PostavshikDTO postavshikDto) {

        naklad = nakladService.save(naklad, postavshikDto.getId(), dogovorDTO.getId());

        return String.format(REDIRECT_URL, naklad.getId());
    }

    @PostMapping("/update/{id}")
    public String updateNaklad(@ModelAttribute(name = "naklad") NakladDTO naklad,
                               @ModelAttribute(name = "dogovorDto") DogovorDTO dogovorDTO,
                               @ModelAttribute(name = "postavshikDto") PostavshikDTO postavshikDto,
                               @PathVariable Long id) {

        naklad = nakladService.update(id, naklad, postavshikDto.getId(), dogovorDTO.getId());

        return String.format(REDIRECT_URL, naklad.getId());
    }

    @PostMapping("/sostav/create/{nakladId}")
    public String createSostav(@PathVariable Long nakladId,
                               @ModelAttribute(name = "sostav") SostavPostavDTO sostav,
                               @ModelAttribute(name = "productDto") ProductDTO productDTO) {

        sostavPostavService.save(nakladId, productDTO.getId(), sostav);

        return String.format(REDIRECT_URL, nakladId);
    }

    @GetMapping("/sostav/delete/{id}")
    public String deleteSostav(@PathVariable Long id) {
        Long nakladId = sostavPostavService.get(id).getTovarNaklad().getId();

        sostavPostavService.delete(id);
        return String.format(REDIRECT_URL, nakladId);
    }

    @GetMapping("/delete/{id}")
    public String deleteNaklad(@PathVariable Long id) {

        nakladService.delete(id);

        return String.format(REDIRECT_URL, id);
    }
}
