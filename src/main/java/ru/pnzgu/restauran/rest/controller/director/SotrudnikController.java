package ru.pnzgu.restauran.rest.controller.director;

import ru.pnzgu.restauran.dto.SotrudnikDTO;
import ru.pnzgu.restauran.rest.service.director.SotrudnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/director/sotrudnik")
public class SotrudnikController {

    final SotrudnikService sotrudnikService;

    @GetMapping("/readAll")
    public ResponseEntity<List<SotrudnikDTO>> readAll() {
        return new ResponseEntity<>(sotrudnikService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<SotrudnikDTO> readPostavshik(@PathVariable Long id) {
        return new ResponseEntity<>(sotrudnikService.get(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SotrudnikDTO> addPostavshik(@RequestBody SotrudnikDTO sotrudnikDTO) {
        return new ResponseEntity<>(sotrudnikService.save(sotrudnikDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SotrudnikDTO> updatePostavshik(@PathVariable Long id, @RequestBody SotrudnikDTO sotrudnikDTO) {
        return new ResponseEntity<>(sotrudnikService.update(id, sotrudnikDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deletePostavshik(@PathVariable Long id) {
        sotrudnikService.delete(id);
        return HttpStatus.OK;
    }
}
