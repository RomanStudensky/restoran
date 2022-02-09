package ru.pnzgu.restauran.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pnzgu.restauran.dto.SotrudnikDTO;
import ru.pnzgu.restauran.store.Role;

import java.util.Arrays;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
@RequestMapping("")
@RequiredArgsConstructor
public class Controller {
    @GetMapping()
    public String getAuthView(Model model) {
        model.addAttribute("authList", Arrays.stream(Role.values()).map(Role::getValue).collect(Collectors.toList()));
        model.addAttribute("sotrudnik", new SotrudnikDTO());
        return "auth";
    }

    @PostMapping("/auth")
    public String auth(@ModelAttribute(name = "sotrudnik") SotrudnikDTO sotrudnikDTO) {

        String redirectUrl = "";
        switch (sotrudnikDTO.getPost()) {
            case "Администратор": redirectUrl = "/admin";
                break;
            case "Бармен": redirectUrl = "/barman/prodaza";
                break;
            case "Бухгалтер": redirectUrl = "/director/postavshik";
                break;
            case "Официант": redirectUrl = "/oficiant/stol";
                break;
            case "Повар": redirectUrl = "/povar/akt";
        }

        return String.format("redirect:%s", redirectUrl);
    }
}
