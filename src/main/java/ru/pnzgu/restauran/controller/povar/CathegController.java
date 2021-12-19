package ru.pnzgu.restauran.controller.povar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.*;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.service.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/povar/catheg")
@RequiredArgsConstructor
public class CathegController {

    private final String REDIRECT_HOME_URL = "redirect:/povar/categ";
    private final String REDIRECT_CATEG_URL = "redirect:/povar/categ/%s";
    private final String REDIRECT_MENU_URL = "redirect:/povar/categ/menu/%s/%s";
    private final String COMMON_VIEW = "/povar/categ/commonView";

    private final String CREATE_CATEG_VIEW = "/povar/categ/action/categ/create";
    private final String UPDATE_CATEG_VIEW = "/povar/categ/action/categ/update";

    private final String CREATE_MENU_VIEW = "/povar/categ/action/menu/create";
    private final String UPDATE_MENU_VIEW = "/povar/categ/action/menu/create";

    private final String CREATE_SOSTAV_MENU_VIEW = "/povar/categ/action/sostav/create";
    private final String UPDATE_SOSTAV_MENU_VIEW = "/povar/categ/action/sostav/create";

    private final CategService categService;
    private final SostavBludoService sostavBludoService;
    private final MenuService menuService;
    private final ProductService productService;


    @GetMapping()
    public String getCommonView(Model model) {
        model.addAttribute("categList", categService.getAll());
        model.addAttribute("menuList", new ArrayList<MenuDTO>());
        model.addAttribute("sostavList", new ArrayList<SostavBludoDTO>());

        return COMMON_VIEW;
    }

    @GetMapping("/{categId}")
    public String getCommonView(@PathVariable Long categId, Model model) {
        try {
            categService.get(categId);
        } catch (NotFoundException e) {
            return REDIRECT_HOME_URL;
        }
        model.addAttribute("categList", categService.getAll());
        model.addAttribute("menuList", menuService.getAllByCategId(categId));
        model.addAttribute("sostavList", new ArrayList<SostavBludoDTO>());
        model.addAttribute("categId", categId);
        return COMMON_VIEW;
    }

    @GetMapping("/{categId}/{menuId}")
    public String getCommonView(@PathVariable Long categId, @PathVariable Long menuId, Model model) {
        try {
            menuService.get(menuId);
        } catch (NotFoundException e) {
            return String.format(REDIRECT_CATEG_URL, categId);
        }

        model.addAttribute("categList", categService.getAll());
        model.addAttribute("menuList", menuService.getAllByCategId(categId));
        model.addAttribute("sostavList", sostavBludoService.getAllSostavByMenuId(menuId));
        model.addAttribute("categId", categId);
        model.addAttribute("menuId", menuId);
        return COMMON_VIEW;
    }

    @GetMapping("/create/view")
    public String getCategCreateView(Model model) {
        model.addAttribute("categ", new CategoryDTO());

        return CREATE_CATEG_VIEW;
    }

    @GetMapping("/update/view/{id}")
    public String getNakladUpdateView(@PathVariable Long id, Model model) {
        CategoryDTO categoryDTO = categService.get(id);
        model.addAttribute("categ", categoryDTO);

        return UPDATE_CATEG_VIEW;
    }

    @GetMapping("/menu/create/view/{categId}")
    public String getMenuCreateView(@PathVariable Long categId, Model model) {
        model.addAttribute("menu", new MenuDTO());
        model.addAttribute("categ", categService.get(categId));

        return CREATE_MENU_VIEW;
    }

    @GetMapping("/menu/update/view/{menuId}")
    public String getMenuUpdateView(@PathVariable Long menuId, Model model) {
        model.addAttribute("menu", menuService.get(menuId));
        model.addAttribute("menuId", menuId);

        return UPDATE_MENU_VIEW;
    }

    @GetMapping("/menu/sostav/create/view/{menuId}")
    public String getSostavCreateView(@PathVariable Long menuId, Model model) {
        model.addAttribute("sostav", new SostavBludoDTO());
        model.addAttribute("menuId", menuId);
        model.addAttribute("productDto", new ProductDTO());
        model.addAttribute("productList", productService.getAll());

        return CREATE_SOSTAV_MENU_VIEW;
    }

    @GetMapping("/menu/sostav/update/view/{sostavId}")
    public String getSostavUpdateView(@PathVariable Long sostavId, Model model) {
        model.addAttribute("sostav", sostavBludoService.get(sostavId));
        model.addAttribute("nakladId", sostavId);
        model.addAttribute("productDto", new ProductDTO());
        model.addAttribute("productList", productService.getAll());

        return UPDATE_SOSTAV_MENU_VIEW;
    }

    // ------------- REST ------------

    @PostMapping("/create")
    public String createCateg(@ModelAttribute(name = "categ") CategoryDTO categoryDTO) {

        categoryDTO = categService.save(categoryDTO);

        return String.format(REDIRECT_CATEG_URL, categoryDTO.getId());
    }

    @PostMapping("/update/{id}")
    public String updateCateg(@ModelAttribute(name = "categ") CategoryDTO categoryDTO, @PathVariable Long id) {

        categoryDTO = categService.update(id, categoryDTO);

        return String.format(REDIRECT_CATEG_URL, categoryDTO.getId());
    }

    @GetMapping("/delete/{id}")
    public String deleteCateg(@PathVariable Long id) {
        categService.delete(id);

        return REDIRECT_HOME_URL;
    }

    @PostMapping("/menu/create/{categId}")
    public String createMenu(@PathVariable Long categId,
                               @ModelAttribute(name = "menu") MenuDTO menuDTO,
                               @ModelAttribute(name = "product") ProductDTO productDTO) {

        menuDTO = menuService.save(menuDTO, categId);

        return String.format(REDIRECT_MENU_URL, categId, menuDTO.getId());
    }

    @PostMapping("/menu/update/{menuId}")
    public String updateMenu(@PathVariable Long menuId,
                               @ModelAttribute(name = "menu") MenuDTO menuDTO) {

        menuService.update(menuId, menuDTO);
        Long categId = menuService.getCategIdByMenuId(menuId);

        return String.format(REDIRECT_MENU_URL, menuId, categId);
    }

    @GetMapping("/menu/delete/{id}")
    public String deleteMenu(@PathVariable Long id) {
        Long categId = menuService.getCategIdByMenuId(id);

        menuService.delete(id);

        return String.format(REDIRECT_CATEG_URL, categId);
    }

    @PostMapping("/menu/sostav/create/{menuId}")
    public String createSostav(@PathVariable Long menuId,
                               @ModelAttribute(name = "sostav") SostavBludoDTO sostavBludoDTO,
                               @ModelAttribute(name = "product") ProductDTO productDTO) {

        sostavBludoService.save(sostavBludoDTO, menuId, productDTO.getId());
        Long categId = menuService.getCategIdByMenuId(menuId);

        return String.format(REDIRECT_MENU_URL, menuId, categId);
    }

    @PostMapping("/menu/sostav/update/{sostavId}")
    public String updateSostav(@PathVariable Long sostavId,
                               @ModelAttribute(name = "sostav") SostavBludoDTO sostav,
                               @ModelAttribute(name = "productDto") ProductDTO productDTO) {

        sostav = sostavBludoService.update(sostavId, sostav, productDTO.getId());

        return String.format(REDIRECT_MENU_URL, sostav.getMenu().getCategory().getId(), sostav.getMenu().getId());
    }

    @GetMapping("/menu/sostav/delete/{id}")
    public String deleteSostav(@PathVariable Long id) {
        Long menuId = sostavBludoService.getMenuIdBySostavId(id);
        Long categId = menuService.getCategIdByMenuId(menuId);
        sostavBludoService.delete(id);

        return String.format(REDIRECT_MENU_URL, categId, menuId);
    }


}
