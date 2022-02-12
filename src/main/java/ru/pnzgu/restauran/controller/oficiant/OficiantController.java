package ru.pnzgu.restauran.controller.oficiant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pnzgu.restauran.dto.ReservDTO;
import ru.pnzgu.restauran.service.ReservService;
import ru.pnzgu.restauran.service.StolService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/oficiant/stol")
@RequiredArgsConstructor
public class OficiantController {

    private final StolService stolService;
    private final ReservService reservService;

    @GetMapping("")
    public String getCommonView(Model model) {
        model.addAttribute("stolList", stolService.getAllStol());

        List<ReservDTO> reservList = new ArrayList<>();
        model.addAttribute("reservList", reservList);

        return "/oficiant/stol/stolReservView";
    }

    @GetMapping("/{stolId}")
    public String getCommonView(@PathVariable Long stolId, Model model) {
        model.addAttribute("stolList", stolService.getAllStol());

        List<ReservDTO> reservList = stolService.getStol(stolId).getReservs();
        model.addAttribute("reservList", reservList);
        model.addAttribute("stolId", stolId);

        return "/oficiant/stol/stolReservView";
    }
}
