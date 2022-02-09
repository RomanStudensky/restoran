package ru.pnzgu.restauran.controller.barman;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.*;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.service.MenuService;
import ru.pnzgu.restauran.service.ProdazaService;
import ru.pnzgu.restauran.service.SostavProdService;
import ru.pnzgu.restauran.service.SotrudnikService;

import java.util.List;

@Controller
@RequestMapping("/barman/prodaza")
@RequiredArgsConstructor
public class BarmanController {

    private final ProdazaService prodazaService;
    private final SostavProdService sostavProdService;
    private final SotrudnikService sotrudnikService;
    private final MenuService menuService;

    @GetMapping()
    public String getCommonView(Model model) {
        model.addAttribute("prodazaList", prodazaService.getAll());

        return "/barman/prodaza/prodazaView";
    }

    @GetMapping("/{prodazaId}")
    public String getCommonView(@PathVariable Long prodazaId, Model model) {
        try {
            prodazaService.get(prodazaId);
        } catch (NotFoundException e) {
            return "redirect:/barman/prodaza";
        }
        model.addAttribute("prodazaList", prodazaService.getAll());
        List<SostavProdDTO> sostavList = sostavProdService.getAllSostavByProdazaId(prodazaId);
        model.addAttribute("sostavList", sostavList);
        model.addAttribute("prodazaId", prodazaId);



        return "/barman/prodaza/prodazaView";
    }

    @GetMapping("/create/view")
    public String getprodazaCreateView(Model model) {
        model.addAttribute("prodaza", new ProdazaDTO());
        model.addAttribute("sotrudnikList", sotrudnikService.getAll());
        model.addAttribute("sotrudnikDto", new SotrudnikDTO());

        return "/barman/prodaza/action/prodaza/create";
    }

    @GetMapping("/update/view/{id}")
    public String getprodazaUpdateView(@PathVariable Long id, Model model) {
        ProdazaDTO prodaza = prodazaService.get(id);
        model.addAttribute("prodaza", prodaza);
        model.addAttribute("sotrudnikList", sotrudnikService.getAll());
        model.addAttribute("sotrudnikDto", prodaza.getSotrud());

        return "/barman/prodaza/action/prodaza/update";
    }


    @GetMapping("/sostav/create/view/{prodazaId}")
    public String getSostavCreateView(@PathVariable Long prodazaId, Model model) {
        model.addAttribute("sostav", new SostavProdDTO());
        model.addAttribute("prodazaId", prodazaId);
        model.addAttribute("menuDto", new MenuDTO());
        model.addAttribute("menuList", menuService.getAll());

        return "/barman/prodaza/action/sostav/create";
    }

    // ------------- REST ------------

    @PostMapping("/create")
    public String createprodaza(@ModelAttribute(name = "prodaza") ProdazaDTO prodaza,
                                @ModelAttribute(name = "stolDto") StolDTO stolDto,
                                @ModelAttribute(name = "sotrudnikDto") SotrudnikDTO sotrudnikDto) {

        prodaza = prodazaService.save(prodaza, sotrudnikDto.getId());

        return String.format("redirect:/barman/prodaza/%s", prodaza.getId());
    }

    @PostMapping("/update/{id}")
    public String updateprodaza(@ModelAttribute(name = "prodaza") ProdazaDTO prodaza,
                                @ModelAttribute(name = "stolDto") StolDTO stolDto,
                                @ModelAttribute(name = "sotrudnikDto") SotrudnikDTO sotrudnikDto,
                                @PathVariable Long id) {

        prodaza = prodazaService.update(id, prodaza, sotrudnikDto.getId());

        return String.format("redirect:/barman/prodaza/%s", prodaza.getId());
    }

    @PostMapping("/sostav/create/{prodazaId}")
    public String createSostav(@PathVariable Long prodazaId,
                               @ModelAttribute(name = "sostav") SostavProdDTO sostav,
                               @ModelAttribute(name = "menuDto") MenuDTO menuDTO) {

        sostavProdService.save(sostav,prodazaId, menuDTO.getId());

        return String.format("redirect:/barman/prodaza/%s", prodazaId);
    }

    @GetMapping("/sostav/delete/{id}")
    public String deleteSostav(@PathVariable Long id) {
        Long prodazaId = sostavProdService.getSostav(id).getProdaza().getId();
        sostavProdService.delete(id);

        return String.format("redirect:/barman/prodaza/%s", prodazaId);
    }

    @GetMapping("/delete/{id}")
    public String deleteprodaza(@PathVariable Long id) {
        Long prodazaId = prodazaService.getFirstProdaza();

        prodazaService.delete(id);

        return String.format("redirect:/barman/prodaza/%s", prodazaId);
    }


}