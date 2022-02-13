package ru.pnzgu.restauran.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pnzgu.restauran.service.UserService;
import ru.pnzgu.restauran.store.Role;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class AuthController {

    private final UserService userService;

    @GetMapping
    public String welcomePage() {
        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/auth/success")
    public String getSuccessPage(Authentication authentication) {
        Role role = userService.getByUsername(authentication.getName()).getRole();
        String redirectUrl = "";

        switch (role) {
            case DIRECTOR_BOOKER:
                redirectUrl = "/director/postavshik";
                break;
            case COOK:
                redirectUrl = "/cook/akt";
                break;
            case ADMIN:
                redirectUrl = "/admin";
                break;
            case OFFICIANT:
                redirectUrl = "/officiant/stol";
                break;
            case BARMAN:
                redirectUrl = "/barman/prodaza";
                break;
        }

        return String.format("redirect:%s", redirectUrl);

    }

}
