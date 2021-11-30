package ru.pnzgu.restauran.rest.controller.povar.order;

import ru.pnzgu.restauran.dto.OrdersDTO;
import ru.pnzgu.restauran.rest.service.povar.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/povar/order")
public class OrderController {

    final OrderService stolService;

    @GetMapping("/readAll")
    public ResponseEntity<List<OrdersDTO>> readAll() {
        return new ResponseEntity<>(stolService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<OrdersDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(stolService.get(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OrdersDTO> create(@RequestBody OrdersDTO ordersDTO) {
        return new ResponseEntity<>(stolService.save(ordersDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrdersDTO> update(@PathVariable Long id, @RequestBody OrdersDTO ordersDTO) {
        return new ResponseEntity<>(stolService.update(id, ordersDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        stolService.delete(id);
        return HttpStatus.OK;
    }
}
