package ru.pnzgu.restauran.controller.director;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.*;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.service.*;
import ru.pnzgu.restauran.store.entity.Dogovor;

import java.util.List;

@Controller
@RequestMapping("/director/postavshik")
@RequiredArgsConstructor
public class PostavshikController {

    private final String REDIRECT_URL = "redirect:/director/postavshik/%s";
    private final String COMMON_VIEW =  "/director/postavshik/postavshikView";
    private final String CREATE_POSTAVSHIK_VIEW = "/director/postavshik/action/postavshik/create";
    private final String UPDATE_POSTAVSHIK_VIEW = "/director/postavshik/action/postavshik/update";
    private final String CREATE_DOGOVOR_VIEW = "/director/postavshik/action/dogovor/create";
    private final String UPDATE_DOGOVOR_VIEW = "/director/postavshik/action/dogovor/update";

    private final PostavshikService postavshikService;
    private final DogovorService dogovorService;


    @GetMapping()
    public String getCommonView(Model model) {
        model.addAttribute("postavshikList", postavshikService.getAll());

        return "/director/postavshik/postavshikView";
    }

    @GetMapping("/{postavshikId}")
    public String getCommonView(@PathVariable Long postavshikId, Model model) {
        try {
            postavshikService.get(postavshikId);
        } catch (NotFoundException e) {
            return "redirect:/director/postavshik";
        }
        model.addAttribute("postavshikList", postavshikService.getAll());

        List<DogovorDTO> dogovorList =
                dogovorService.getAllByPostavshikId(postavshikId);

        model.addAttribute("dogovorList", dogovorList);
        model.addAttribute("postavshikId", postavshikId);

        return COMMON_VIEW;
    }

    @GetMapping("/create/view")
    public String getPostavshikCreateView(Model model) {
        model.addAttribute("postavshik", new PostavshikDTO());

        return CREATE_POSTAVSHIK_VIEW;
    }

    @GetMapping("/update/view/{id}")
    public String getPostavshikUpdateView(@PathVariable Long id, Model model) {
        PostavshikDTO postavshikDTO = postavshikService.get(id);
        model.addAttribute("postavshik", postavshikDTO);

        return UPDATE_POSTAVSHIK_VIEW;
    }


    @GetMapping("/dogovor/create/view/{postavshikId}")
    public String getDogovorCreateView(@PathVariable Long postavshikId, Model model) {
        model.addAttribute("dogovor", new DogovorDTO());
        model.addAttribute("postavshikId", postavshikId);

        return CREATE_DOGOVOR_VIEW;
    }

    @GetMapping("/dogovor/update/view/{dogovorId}")
    public String getDogovorUpdateView(@PathVariable Long dogovorId, Model model) {
        DogovorDTO dogovorDTO = dogovorService.get(dogovorId);
        model.addAttribute("dogovorId", dogovorId);
        model.addAttribute("dogovor", dogovorDTO);

        return UPDATE_DOGOVOR_VIEW;
    }

    // ------------- REST ------------

    @PostMapping("/create")
    public String createPostavshik(@ModelAttribute(name = "postavshik") PostavshikDTO postavshik,
                               @ModelAttribute(name = "dogovorDto") DogovorDTO dogovorDTO,
                               @ModelAttribute(name = "postavshikDto") PostavshikDTO postavshikDto) {

        postavshik = postavshikService.save(postavshik);

        return String.format(REDIRECT_URL, postavshik.getId());
    }

    @PostMapping("/update/{id}")
    public String updatePostavshik(@ModelAttribute(name = "postavshik") PostavshikDTO postavshik,
                                @PathVariable Long id) {

        postavshik = postavshikService.update(id, postavshik);

        return String.format(REDIRECT_URL, postavshik.getId());
    }

    @PostMapping("/dogovor/create/{postavshikId}")
    public String createDogovor(@PathVariable Long postavshikId,
                               @ModelAttribute(name = "dogovor") DogovorDTO dogovor) {

        dogovorService.save(postavshikId, dogovor);

        return String.format(REDIRECT_URL, postavshikId);
    }

    @PostMapping("/dogovor/update/{dogovorId}")
    public String updateeDogovor(@PathVariable Long dogovorId,
                                @ModelAttribute(name = "dogovor") DogovorDTO dogovor) {

        Long postavshikId = dogovorService.update(dogovorId, dogovor).getPostavshik().getId();

        return String.format(REDIRECT_URL, postavshikId);
    }

    @GetMapping("/dogovor/delete/{id}")
    public String deleteDogovor(@PathVariable Long id) {
        Long postavshikId = dogovorService.get(id).getPostavshik().getId();
        dogovorService.delete(id);

        return String.format(REDIRECT_URL, postavshikId);
    }

    @GetMapping("/delete/{id}")
    public String deletePostavshik(@PathVariable Long id) {
        Long postavshikId = postavshikService.getFirstPostavshik();

        postavshikService.delete(id);

        return String.format(REDIRECT_URL, postavshikId);
    }


}

