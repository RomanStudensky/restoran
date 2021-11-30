package ru.pnzgu.restauran.rest.controller.povar.naklad;

import ru.pnzgu.restauran.dto.SostavPostavDTO;
import ru.pnzgu.restauran.rest.service.povar.SostavPostavService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/povar/sostavPostav")
public class SostavPostavController {

    final SostavPostavService sostavPostavService;

    @GetMapping("/readAllSostavByProdazaId/{id}")
    public ResponseEntity<List<SostavPostavDTO>> readAllReservByStolId(@PathVariable Long id) {
        return new ResponseEntity<>(sostavPostavService.getAllSostavBySostavNaklId(id), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<SostavPostavDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(sostavPostavService.get(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SostavPostavDTO> create(@RequestBody SostavPostavDTO sostavPostavDTO) {
        return new ResponseEntity<>(sostavPostavService.save(sostavPostavDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SostavPostavDTO> update(@PathVariable Long id, @RequestBody SostavPostavDTO sostavPostavDTO) {
        return new ResponseEntity<>(sostavPostavService.update(id, sostavPostavDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        sostavPostavService.delete(id);
        return HttpStatus.OK;
    }
}
