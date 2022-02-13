package ru.pnzgu.restauran.controller.director;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.*;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.service.*;
import ru.pnzgu.restauran.store.Role;

import java.util.List;

@Controller
@RequestMapping("/director/sotrudnik")
@RequiredArgsConstructor
public class SotrudnikController {

    private final String REDIRECT_URL = "redirect:/director/sotrudnik/%s";
    private final String COMMON_VIEW =  "/director/sotrudnik/sotrudnikView";
    private final String CREATE_SOTRUDNIK_VIEW = "/director/sotrudnik/action/sotrudnik/create";
    private final String UPDATE_SOTRUDNIK_VIEW = "/director/sotrudnik/action/sotrudnik/update";

    private final UserService sotrudnikService;

    @GetMapping()
    public String getCommonView(Model model) {
        model.addAttribute("sotrudnikList", sotrudnikService.getAll());

        return COMMON_VIEW;
    }

    @GetMapping("/{sotrudnikId}")
    public String getCommonView(@PathVariable Long sotrudnikId, Model model) {
        try {
            sotrudnikService.get(sotrudnikId);
        } catch (NotFoundException e) {
            return "redirect:/director/sotrudnik";
        }
        model.addAttribute("sotrudnikList", sotrudnikService.getAll());

        model.addAttribute("sotrudnikId", sotrudnikId);

        return COMMON_VIEW;
    }

    @GetMapping("/create/view")
    public String getSotrudnikCreateView(Model model) {
        model.addAttribute("sotrudnik", new UserDto());
        model.addAttribute("roleList", List.of(Role.values()));

        return CREATE_SOTRUDNIK_VIEW;
    }

    @GetMapping("/update/view/{id}")
    public String getsotrudnikUpdateView(@PathVariable Long id, Model model) {
        UserDto sotrudnikDTO = sotrudnikService.get(id);
        model.addAttribute("sotrudnik", sotrudnikDTO);
        model.addAttribute("roleList", List.of(Role.values()));

        return UPDATE_SOTRUDNIK_VIEW;
    }

    // ------------- REST ------------

    @PostMapping("/create")
    public String createSotrudnik(@ModelAttribute(name = "sotrudnik") UserDto sotrudnik) {

        sotrudnik = sotrudnikService.save(sotrudnik);

        return String.format(REDIRECT_URL, sotrudnik.getId());
    }

    @PostMapping("/update/{id}")
    public String updateSotrudnik(@ModelAttribute(name = "sotrudnik") UserDto sotrudnik, @PathVariable Long id) {

        sotrudnik = sotrudnikService.update(id, sotrudnik);

        return String.format(REDIRECT_URL, sotrudnik.getId());
    }
    

    @GetMapping("/delete/{id}")
    public String deleteSotrudnik(@PathVariable Long id) {
        sotrudnikService.delete(id);

        return "redirect:/director/sotrudnik";
    }


}
