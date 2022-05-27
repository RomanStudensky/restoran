package ru.pnzgu.restauran.controller.cook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.*;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.service.*;

import java.util.List;

@Controller
@RequestMapping("/cook/order")
@RequiredArgsConstructor
public class OrdersController {

    private final String REDIRECT_URL = "redirect:/cook/order/%s";
    private final String COMMON_VIEW =  "/cook/order/commonView";
    private final String CREATE_ORDER_VIEW = "/cook/order/action/order/create";
    private final String UPDATE_ORDER_VIEW = "/cook/order/action/order/update";
    private final String CREATE_SOSTAV_POSTAV_VIEW = "/cook/order/action/sostav/create";

    private final OrderService orderService;
    private final SostavOrderService sostavOrderService;
    private final DogovorService dogovorService;
    private final PostavshikService postavshikService;
    private final ProductService productService;

    private final DocumentService documentService;


    @GetMapping()
    public String getCommonView(Model model) {
        model.addAttribute("orderList", orderService.getAll());

        return COMMON_VIEW;
    }

    @GetMapping("/{orderId}")
    public String getCommonView(@PathVariable Long orderId, Model model) {
        try {
            orderService.get(orderId);
        } catch (NotFoundException e) {
            return "redirect:/cook/order";
        }
        model.addAttribute("orderList", orderService.getAll());

        List<SostavOrderDTO> sostavList = sostavOrderService.getAllByOrderId(orderId);
        model.addAttribute("sostavList", sostavList);
        model.addAttribute("orderId", orderId);

        return COMMON_VIEW;
    }

    @GetMapping("/create/view")
    public String getOrderCreateView(Model model) {
        model.addAttribute("order", new OrdersDTO());
        model.addAttribute("postavshikList", postavshikService.getAll());
        model.addAttribute("dogovorList", dogovorService.getAll());
        model.addAttribute("postavshikDto", new PostavshikDTO());
        model.addAttribute("dogovorDto", new DogovorDTO());

        return CREATE_ORDER_VIEW;
    }

    @GetMapping("/update/view/{id}")
    public String getOrderUpdateView(@PathVariable Long id, Model model) {
        OrdersDTO ordersDTO = orderService.get(id);
        model.addAttribute("order", ordersDTO);
        model.addAttribute("postavshikList", postavshikService.getAll());
        model.addAttribute("dogovorList", dogovorService.getAll());
        model.addAttribute("postavshikDto", ordersDTO.getPostavshik());
        model.addAttribute("dogovorDto", ordersDTO.getDogovor());

        return UPDATE_ORDER_VIEW;
    }


    @GetMapping("/sostav/create/view/{orderId}")
    public String getSostavCreateView(@PathVariable Long orderId, Model model) {
        model.addAttribute("sostav", new SostavOrderDTO());
        model.addAttribute("orderId", orderId);
        model.addAttribute("productDto", new ProductDTO());
        model.addAttribute("productList", productService.getAll());

        return CREATE_SOSTAV_POSTAV_VIEW;
    }

    // ------------- REST ------------

    @PostMapping("/create")
    public String createOrder(@ModelAttribute(name = "order") OrdersDTO order,
                               @ModelAttribute(name = "dogovorDto") DogovorDTO dogovorDTO,
                               @ModelAttribute(name = "postavshikDto") PostavshikDTO postavshikDto) {

        order = orderService.save(order, postavshikDto.getId(), dogovorDTO.getId());

        return String.format(REDIRECT_URL, order.getId());
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@ModelAttribute(name = "order") OrdersDTO order,
                               @ModelAttribute(name = "dogovorDto") DogovorDTO dogovorDTO,
                               @ModelAttribute(name = "postavshikDto") PostavshikDTO postavshikDto,
                               @PathVariable Long id) {

        order = orderService.update(id, order, postavshikDto.getId(), dogovorDTO.getId());

        return String.format(REDIRECT_URL, order.getId());
    }

    @PostMapping("/sostav/create/{orderId}")
    public String createSostav(@PathVariable Long orderId,
                               @ModelAttribute(name = "sostav") SostavOrderDTO sostav,
                               @ModelAttribute(name = "productDto") ProductDTO productDTO) {

        sostavOrderService.save(sostav, productDTO.getId(), orderId);

        return String.format(REDIRECT_URL, orderId);
    }

    @GetMapping("/sostav/delete/{id}")
    public String deleteSostav(@PathVariable Long id) {
        Long orderId = sostavOrderService.get(id).getOrders().getId();
        sostavOrderService.delete(id);

        return String.format(REDIRECT_URL, orderId);
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        Long orderId = orderService.getFirstOrder();

        orderService.delete(id);

        return String.format(REDIRECT_URL, orderId);
    }

}
