package ru.pnzgu.restauran.rest.controller.povar.akt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.AktDTO;
import ru.pnzgu.restauran.dto.DtoParent;
import ru.pnzgu.restauran.rest.service.povar.AktService;

import java.util.List;

@RestController
@RequestMapping("/api/povar/akt")
@RequiredArgsConstructor
public class AktController {

    final AktService aktService;

    @GetMapping("/readAll")
    public ResponseEntity<List<AktDTO>> readAll() {
        return new ResponseEntity<>(aktService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<DtoParent> read(@PathVariable Long id) {
        return new ResponseEntity<>(aktService.get(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DtoParent> create(@RequestBody AktDTO dto) {
        return new ResponseEntity<>(aktService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DtoParent> update(@PathVariable Long id, @RequestBody AktDTO dto) {
        return new ResponseEntity<>(aktService.update(id, dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        aktService.delete(id);
        return HttpStatus.OK;
    }
}
