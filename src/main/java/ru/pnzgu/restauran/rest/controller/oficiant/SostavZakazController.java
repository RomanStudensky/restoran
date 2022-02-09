package ru.pnzgu.restauran.rest.controller.oficiant;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.SostavZakazDTO;
import ru.pnzgu.restauran.rest.service.oficiant.SostavZakazService;

import java.util.List;

@RestController
@RequestMapping("/api/oficiant/sostavZakaz")
@RequiredArgsConstructor
public class SostavZakazController {

    final SostavZakazService sostavZakazService;

    @GetMapping("/readAllSostavByZakazId/{id}")
    public ResponseEntity<List<SostavZakazDTO>> readAllReservByStolId(@PathVariable Long id) {
        return new ResponseEntity<>(sostavZakazService.getAllSostavZakazByZakazId(id), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<SostavZakazDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(sostavZakazService.getSostav(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SostavZakazDTO> create(@RequestBody SostavZakazDTO sostavZakazDTO) {
        return new ResponseEntity<>(sostavZakazService.save(sostavZakazDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SostavZakazDTO> update(@PathVariable Long id, @RequestBody SostavZakazDTO sostavZakazDTO) {
        return new ResponseEntity<>(sostavZakazService.update(id, sostavZakazDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        sostavZakazService.delete(id);
        return HttpStatus.OK;
    }
}
