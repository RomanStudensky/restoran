package ru.pnzgu.restauran.rest.controller.oficiant;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.ZakazDTO;
import ru.pnzgu.restauran.rest.service.oficiant.ZakazService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oficiant/zakaz")
public class ZakazController {

    final ZakazService zakazService;

    @GetMapping("/readAll")
    public ResponseEntity<List<ZakazDTO>> readAll() {
        return new ResponseEntity<>(zakazService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ZakazDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(zakazService.get(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ZakazDTO> create(@RequestBody ZakazDTO zakazDTO) {
        return new ResponseEntity<>(zakazService.save(zakazDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ZakazDTO> update(@PathVariable Long id, @RequestBody ZakazDTO zakazDTO) {
        return new ResponseEntity<>(zakazService.update(id, zakazDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        zakazService.delete(id);
        return HttpStatus.OK;
    }
}
