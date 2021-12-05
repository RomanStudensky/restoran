package ru.pnzgu.restauran.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.ReservDTO;
import ru.pnzgu.restauran.dto.StolDTO;
import ru.pnzgu.restauran.rest.service.ReservService;
import ru.pnzgu.restauran.rest.service.StolService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final StolService stolService;
    private final ReservService reservService;

    @GetMapping("")
    public String getCommonView(Model model) {
        model.addAttribute("stolList", stolService.getAllStol());

        List<ReservDTO> reservList = new ArrayList<>();
        model.addAttribute("reservList", reservList);

        return "/admin/commonAdminView";
    }

    @GetMapping("/{stolId}")
    public String getCommonView(@PathVariable Long stolId, Model model) {
        model.addAttribute("stolList", stolService.getAllStol());

        List<ReservDTO> reservList = stolService.getStol(stolId).getReservs();
        model.addAttribute("reservList", reservList);
        model.addAttribute("stolId", stolId);

        return "/admin/commonAdminView";
    }

    @GetMapping("/stol/create/view")
    public String getStolCreateView(Model model) {
        model.addAttribute("stol", new StolDTO());

        return "/admin/action/stol/create";
    }

    @GetMapping("/stol/update/view/{id}")
    public String getStolUpdateView(@PathVariable Long id, Model model) {
        StolDTO stol = stolService.getStol(id);
        model.addAttribute("stol", stol);

        return "/admin/action/stol/update";
    }


    @GetMapping("/reserv/create/view/{stolId}")
    public String getReservCreateView(@PathVariable Long stolId, Model model) {
        model.addAttribute("reserv", new ReservDTO());
        model.addAttribute("stolId", stolId);

        return "/admin/action/reserv/create";
    }


    @GetMapping("/reserv/update/view/{id}")
    public String getReservUpdateView(@PathVariable Long id, Model model) {
        ReservDTO reserv = reservService.get(id);
        model.addAttribute("reserv", reserv);
        model.addAttribute("reservId", id);

        return "/admin/action/reserv/update";
    }


    // ------------- REST ------------

    @PostMapping("/stol/create")
    public String createStol(@ModelAttribute StolDTO stolDTO) {
        stolDTO = stolService.save(stolDTO);

        return String.format("redirect:/admin/%s", stolDTO.getId());
    }

    @PostMapping("/stol/update/{id}")
    public String updateStol(@PathVariable Long id, @ModelAttribute StolDTO stolDTO) {
        stolService.update(id, stolDTO);

        return String.format("redirect:/admin/%s", id);
    }

    @PostMapping("/reserv/create/{stolId}")
    public String createReserv(@PathVariable Long stolId, @ModelAttribute(name = "reserv") ReservDTO reserv) {

        reservService.save(stolId, reserv);

        return String.format("redirect:/admin/%s", stolId);
    }

    @PostMapping("/reserv/update/{id}")
    public String updateReserv(@PathVariable Long id,  @ModelAttribute(name = "reserv") ReservDTO reserv) {

        reservService.update(id, reserv);
        System.out.println("");
        return String.format("redirect:/admin/%s", id);
    }

    @GetMapping("/reserv/delete/{id}")
    public String deleteReserv(@PathVariable Long id) {
        Long stolId = reservService.get(id).getStol().getId();
        reservService.delete(id);

        return String.format("redirect:/admin/%s", stolId);
    }

    @GetMapping("/stol/delete/{id}")
    public String deleteStol(@PathVariable Long id) {
        stolService.delete(id);

        return String.format("redirect:/admin/%s", stolService.getFirstStol());
    }

}
