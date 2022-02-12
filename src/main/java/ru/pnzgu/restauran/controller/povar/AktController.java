package ru.pnzgu.restauran.controller.povar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.*;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.service.*;

import java.util.List;

@Controller
@RequestMapping("/povar/akt")
@RequiredArgsConstructor
public class AktController {

    private final String REDIRECT_URL = "redirect:/povar/akt/%s";
    private final String COMMON_VIEW =  "/povar/akt/commonView";
    private final String CREATE_AKT_VIEW = "/povar/akt/action/akt/create";
    private final String UPDATE_AKT_VIEW = "/povar/akt/action/akt/update";
    private final String CREATE_SOSTAV_VIEW = "/povar/akt/action/sostav/create";

    private final AktService aktService;
    private final SostavAktService sostavAktService;
    private final SotrudnikService sotrudnikService;
    private final ProductService productService;


    @GetMapping()
    public String getCommonView(Model model) {
        model.addAttribute("aktList", aktService.getAll());

        return COMMON_VIEW;
    }

    @GetMapping("/{aktId}")
    public String getCommonView(@PathVariable Long aktId, Model model) {
        try {
            aktService.get(aktId);
        } catch (NotFoundException e) {
            return "redirect:/povar/akt";
        }
        model.addAttribute("aktList", aktService.getAll());

        List<SostavAktDTO> sostavList = sostavAktService.getSostavByAktId(aktId);
        model.addAttribute("sostavList", sostavList);
        model.addAttribute("aktId", aktId);

        return COMMON_VIEW;
    }

    @GetMapping("/create/view")
    public String getNakladCreateView(Model model) {
        model.addAttribute("akt", new AktDTO());
        model.addAttribute("sotrudnikList", sotrudnikService.getAll());
        model.addAttribute("sotrudnikDto", new SotrudnikDTO());
        return CREATE_AKT_VIEW;
    }

    @GetMapping("/update/view/{id}")
    public String getNakladUpdateView(@PathVariable Long id, Model model) {
        AktDTO aktDTO = aktService.get(id);
        model.addAttribute("akt", aktDTO);
        model.addAttribute("sotrudnikList", sotrudnikService.getAll());
        model.addAttribute("sotrudnikDto", new SotrudnikDTO());
        return UPDATE_AKT_VIEW;
    }


    @GetMapping("/sostav/create/view/{aktId}")
    public String getSostavCreateView(@PathVariable Long aktId, Model model) {
        model.addAttribute("sostav", new SostavAktDTO());
        model.addAttribute("aktId", aktId);
        model.addAttribute("productDto", new ProductDTO());
        model.addAttribute("productList", productService.getAll());

        return CREATE_SOSTAV_VIEW;
    }

    // ------------- REST ------------

    @PostMapping("/create")
    public String createAkt(@ModelAttribute(name = "akt") AktDTO aktDTO,
                            @ModelAttribute(name = "sotrudnikDto") SotrudnikDTO sotrudnikDTO) {

        aktDTO = aktService.save(aktDTO, sotrudnikDTO.getId());

        return String.format(REDIRECT_URL, aktDTO.getId());
    }

    @PostMapping("/update/{id}")
    public String updateNaklad(@ModelAttribute(name = "akt") AktDTO aktDTO,
                               @ModelAttribute(name = "sotrudnikDto") SotrudnikDTO sotrudnikDTO,
                               @PathVariable Long id) {

        aktDTO = aktService.update(id, aktDTO, sotrudnikDTO.getId());

        return String.format(REDIRECT_URL, aktDTO.getId());
    }

    @PostMapping("/sostav/create/{aktId}")
    public String createSostav(@PathVariable Long aktId,
                               @ModelAttribute(name = "sostav") SostavAktDTO sostav,
                               @ModelAttribute(name = "productDto") ProductDTO productDTO) {

        sostavAktService.save(aktId, productDTO.getId(), sostav);

        return String.format(REDIRECT_URL, aktId);
    }

    @GetMapping("/sostav/delete/{id}")
    public String deleteSostav(@PathVariable Long id) {
        Long nakladId = sostavAktService.get(id).getAktSpis().getId();
        sostavAktService.delete(id);

        return String.format(REDIRECT_URL, nakladId);
    }

    @GetMapping("/delete/{id}")
    public String deleteNaklad(@PathVariable Long id) {
        Long nakladId = aktService.getFirstAkt();

        aktService.delete(id);

        return String.format(REDIRECT_URL, nakladId);
    }


}
