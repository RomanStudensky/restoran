package ru.pnzgu.restauran.rest.controller.barman;

import ru.pnzgu.restauran.dto.ProdazaDTO;
import ru.pnzgu.restauran.rest.service.barmen.ProdazaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barman/prodaza")
@RequiredArgsConstructor
public class ProdzaController {

    final ProdazaService prodazaService;

    @GetMapping("/readAll")
    public ResponseEntity<List<ProdazaDTO>> readAll() {
        return new ResponseEntity<>(prodazaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ProdazaDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(prodazaService.getProdaza(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProdazaDTO> create(@RequestBody ProdazaDTO prodazaDTO) {
        return new ResponseEntity<>(prodazaService.save(prodazaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProdazaDTO> update(@PathVariable Long id, @RequestBody ProdazaDTO prodazaDTO) {
        return new ResponseEntity<>(prodazaService.update(id, prodazaDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        prodazaService.delete(id);
        return HttpStatus.OK;
    }
}
