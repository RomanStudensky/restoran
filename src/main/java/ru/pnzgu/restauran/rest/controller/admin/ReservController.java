package ru.pnzgu.restauran.rest.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.ReservDTO;
import ru.pnzgu.restauran.rest.service.admin.ReservService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reserv")
@RequiredArgsConstructor
public class ReservController {

    final ReservService reservService;

    @GetMapping("/readAllReservByStolId/{id}")
    public ResponseEntity<List<ReservDTO>> readAllReservByStolId(@PathVariable Long id) {
        return new ResponseEntity<>(reservService.getAllByStolId(id), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ReservDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(reservService.get(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ReservDTO> create(@RequestBody ReservDTO reservDTO) {
        return new ResponseEntity<>(reservService.save(reservDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservDTO> update(@PathVariable Long id, @RequestBody ReservDTO reservDTO) {
        return new ResponseEntity<>(reservService.update(id, reservDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        reservService.delete(id);
        return HttpStatus.OK;
    }
}
