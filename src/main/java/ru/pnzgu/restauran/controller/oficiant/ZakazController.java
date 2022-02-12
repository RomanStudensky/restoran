package ru.pnzgu.restauran.controller.oficiant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.*;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.service.*;

import java.util.List;

@Controller
@RequestMapping("/oficiant/zakaz")
@RequiredArgsConstructor
public class ZakazController {

    private final ZakazService zakazService;
    private final SostavZakazService sostavZakazService;
    private final StolService stolService;
    private final SotrudnikService sotrudnikService;
    private final MenuService menuService;

    @GetMapping()
    public String getCommonView(Model model) {
        model.addAttribute("zakazList", zakazService.getAll());

        return "/oficiant/zakaz/zakazView";
    }

    @GetMapping("/{zakazId}")
    public String getCommonView(@PathVariable Long zakazId, Model model) {
        try {
            zakazService.get(zakazId);
        } catch (NotFoundException e) {
            return "redirect:/oficiant/zakaz";
        }
        model.addAttribute("zakazList", zakazService.getAll());
        List<SostavZakazDTO> sostavList = sostavZakazService.getAllSostavZakazByZakazId(zakazId);
        model.addAttribute("sostavList", sostavList);
        model.addAttribute("zakazId", zakazId);



        return "/oficiant/zakaz/zakazView";
    }

    @GetMapping("/create/view")
    public String getZakazCreateView(Model model) {
        model.addAttribute("zakaz", new ZakazDTO());
        model.addAttribute("sotrudnikList", sotrudnikService.getAll());
        model.addAttribute("stolList", stolService.getAllStol());
        model.addAttribute("sotrudnikDto", new SotrudnikDTO());
        model.addAttribute("stolDto", new StolDTO());

        return "/oficiant/zakaz/action/zakaz/create";
    }

    @GetMapping("/update/view/{id}")
    public String getZakazUpdateView(@PathVariable Long id, Model model) {
        ZakazDTO zakaz = zakazService.get(id);
        model.addAttribute("zakaz", zakaz);
        model.addAttribute("sotrudnikList", sotrudnikService.getAll());
        model.addAttribute("stolList", stolService.getAllStol());
        model.addAttribute("sotrudnikDto", zakaz.getSotrud());
        model.addAttribute("stolDto", zakaz.getStol());

        return "/oficiant/zakaz/action/zakaz/update";
    }


    @GetMapping("/sostav/create/view/{zakazId}")
    public String getSostavCreateView(@PathVariable Long zakazId, Model model) {
        model.addAttribute("sostav", new SostavZakazDTO());
        model.addAttribute("zakazId", zakazId);
        model.addAttribute("menuDto", new MenuDTO());
        model.addAttribute("menuList", menuService.getAll());

        return "/oficiant/zakaz/action/sostav/create";
    }

    // ------------- REST ------------

    @PostMapping("/create")
    public String createZakaz(@ModelAttribute(name = "zakaz") ZakazDTO zakaz,
                              @ModelAttribute(name = "stolDto") StolDTO stolDto,
                              @ModelAttribute(name = "sotrudnikDto") SotrudnikDTO sotrudnikDto) {

        zakaz = zakazService.save(zakaz, sotrudnikDto.getId(), stolDto.getId());

        return String.format("redirect:/oficiant/zakaz/%s", zakaz.getId());
    }

    @PostMapping("/update/{id}")
    public String updateZakaz(@ModelAttribute(name = "zakaz") ZakazDTO zakaz,
                              @ModelAttribute(name = "stolDto") StolDTO stolDto,
                              @ModelAttribute(name = "sotrudnikDto") SotrudnikDTO sotrudnikDto,
                              @PathVariable Long id) {

        zakaz = zakazService.update(zakaz, sotrudnikDto.getId(), stolDto.getId(), id);

        return String.format("redirect:/oficiant/zakaz/%s", zakaz.getId());
    }

    @PostMapping("/sostav/create/{zakazId}")
    public String createSostav(@PathVariable Long zakazId,
                               @ModelAttribute(name = "sostav") SostavZakazDTO sostav,
                               @ModelAttribute(name = "menuDto") MenuDTO menuDTO) {

        sostavZakazService.save(zakazId, menuDTO.getId(), sostav);

        return String.format("redirect:/oficiant/zakaz/%s", zakazId);
    }

    @GetMapping("/sostav/delete/{id}")
    public String deleteSostav(@PathVariable Long id) {
        Long zakazId = sostavZakazService.get(id).getZakaz().getId();
        sostavZakazService.delete(id);

        return String.format("redirect:/oficiant/zakaz/%s", zakazId);
    }

    @GetMapping("/delete/{id}")
    public String deleteZakaz(@PathVariable Long id) {
        Long zakazId = zakazService.getFirstZakaz();

        zakazService.delete(id);

        return String.format("redirect:/oficiant/zakaz/%s", zakazId);
    }


}
