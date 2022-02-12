package ru.pnzgu.restauran.rest.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.StolDTO;
import ru.pnzgu.restauran.rest.service.admin.StolService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/stol")
@RequiredArgsConstructor
public class StolController {

    final StolService stolService;

    @GetMapping("/readAll")
    public ResponseEntity<List<StolDTO>> readAll() {
        return new ResponseEntity<>(stolService.getAllStol(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<StolDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(stolService.getStol(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<StolDTO> create(@RequestBody StolDTO stolDTO) {
        return new ResponseEntity<>(stolService.save(stolDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StolDTO> update(@PathVariable Long id, @RequestBody StolDTO stolDTO) {
        return new ResponseEntity<>(stolService.update(id, stolDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        stolService.delete(id);
        return HttpStatus.OK;
    }

}
