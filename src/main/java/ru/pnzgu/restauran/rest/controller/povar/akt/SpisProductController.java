package ru.pnzgu.restauran.rest.controller.povar.akt;

import ru.pnzgu.restauran.dto.SpisProductDTO;
import ru.pnzgu.restauran.rest.service.povar.SpisProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/povar/spisProduct")
@RequiredArgsConstructor
public class SpisProductController {

    final SpisProductService spisProductService;

//    @GetMapping("/readAllReservByStolId/{id}")
//    public ResponseEntity<List<SpisProductDTO>> readAllReservByStolId(@PathVariable Long id) {
//        return new ResponseEntity<>(spisProductService.getAllByAktId(id), HttpStatus.OK);
//    }

    @GetMapping("/read/{id}")
    public ResponseEntity<SpisProductDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(spisProductService.get(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SpisProductDTO> create(@RequestBody SpisProductDTO spisProductDTO) throws Exception {
        return new ResponseEntity<>(spisProductService.save(spisProductDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SpisProductDTO> update(@PathVariable Long id, @RequestBody SpisProductDTO spisProductDTO) {
        return new ResponseEntity<>(spisProductService.update(id, spisProductDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        spisProductService.delete(id);
        return HttpStatus.OK;
    }
}
