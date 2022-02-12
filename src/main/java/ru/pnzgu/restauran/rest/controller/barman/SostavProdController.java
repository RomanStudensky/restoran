package ru.pnzgu.restauran.rest.controller.barman;

import ru.pnzgu.restauran.dto.SostavProdDTO;
import ru.pnzgu.restauran.rest.service.barmen.SostavProdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/sostavProd")
public class SostavProdController {

    final SostavProdService sostavProdService;

    @GetMapping("/readAllSostavByProdazaId/{id}")
    public ResponseEntity<List<SostavProdDTO>> readAllReservByStolId(@PathVariable Long id) {
        return new ResponseEntity<>(sostavProdService.getAllSostavByProdazaId(id), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<SostavProdDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(sostavProdService.getSostav(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SostavProdDTO> create(@RequestBody SostavProdDTO sostavProdDTO) {
        return new ResponseEntity<>(sostavProdService.save(sostavProdDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SostavProdDTO> update(@PathVariable Long id, @RequestBody SostavProdDTO sostavProdDTO) {
        return new ResponseEntity<>(sostavProdService.update(id, sostavProdDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        sostavProdService.delete(id);
        return HttpStatus.OK;
    }

}
