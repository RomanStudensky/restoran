package ru.pnzgu.restauran.rest.controller.povar.order;

import ru.pnzgu.restauran.dto.SostavOrderDTO;
import ru.pnzgu.restauran.rest.service.povar.SostavOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/povar/sostavOrder")
public class SostavOrderController {

    final SostavOrderService sostavOrderService;

    @GetMapping("/readAllSostavByOrderId/{id}")
    public ResponseEntity<List<SostavOrderDTO>> readAllSostavByOrderId(@PathVariable Long id) {
        return new ResponseEntity<>(sostavOrderService.getAllByOrderId(id), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<SostavOrderDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(sostavOrderService.get(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SostavOrderDTO> create(@RequestBody SostavOrderDTO sostavOrderDTO) {
        return new ResponseEntity<>(sostavOrderService.save(sostavOrderDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SostavOrderDTO> update(@PathVariable Long id, @RequestBody SostavOrderDTO sostavOrderDTO) {
        return new ResponseEntity<>(sostavOrderService.update(id, sostavOrderDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        sostavOrderService.delete(id);
        return HttpStatus.OK;
    }

}
