package ru.pnzgu.restauran.controller.povar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.ProductDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.service.ProductService;

@Controller
@RequestMapping("/povar/product")
@RequiredArgsConstructor
public class ProductController {

    private final String REDIRECT_URL = "redirect:/povar/product/%s";
    private final String COMMON_VIEW =  "/povar/product/commonView";
    private final String CREATE_PRODUCT_VIEW = "/povar/product/action/create";
    private final String UPDATE_PRODUCT_VIEW = "/povar/product/action/update";

    private final ProductService productService;

    @GetMapping()
    public String getCommonView(Model model) {
        model.addAttribute("productList", productService.getAll());

        return COMMON_VIEW;
    }

    @GetMapping("/{productId}")
    public String getCommonView(@PathVariable Long productId, Model model) {
        try {
            productService.get(productId);
        } catch (NotFoundException e) {
            return "redirect:/povar/product";
        }
        model.addAttribute("productList", productService.getAll());

        model.addAttribute("productId", productId);

        return COMMON_VIEW;
    }

    @GetMapping("/create/view")
    public String getProductCreateView(Model model) {
        model.addAttribute("product", new ProductDTO());

        return CREATE_PRODUCT_VIEW;
    }

    @GetMapping("/update/view/{id}")
    public String getProductUpdateView(@PathVariable Long id, Model model) {
        ProductDTO productDTO = productService.get(id);
        model.addAttribute("product", productDTO);

        return UPDATE_PRODUCT_VIEW;
    }

    // ------------- REST ------------

    @PostMapping("/create")
    public String createProduct(@ModelAttribute(name = "product") ProductDTO product) {

        product = productService.save(product);

        return String.format(REDIRECT_URL, product.getId());
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@ModelAttribute(name = "product") ProductDTO product, @PathVariable Long id) {

        product = productService.update(id, product);

        return String.format(REDIRECT_URL, product.getId());
    }


    @GetMapping("/delete/{id}")
    public String deleteproduct(@PathVariable Long id) {
        productService.delete(id);

        return "redirect:/povar/product";
    }


}
