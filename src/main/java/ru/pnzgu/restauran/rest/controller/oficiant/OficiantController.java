package ru.pnzgu.restauran.rest.controller.oficiant;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pnzgu.restauran.dto.ReservDTO;
import ru.pnzgu.restauran.dto.StolDTO;
import ru.pnzgu.restauran.rest.service.admin.ReservService;
import ru.pnzgu.restauran.rest.service.admin.StolService;

import java.util.List;

@RestController
@RequestMapping("/api/oficiant/read")
@RequiredArgsConstructor
public class OficiantController {

    final StolService stolService;
    final ReservService reservService;

    @GetMapping("/readAllStol")
    public ResponseEntity<List<StolDTO>> readAllStol() {
        return new ResponseEntity<>(stolService.getAllStol(), HttpStatus.OK);
    }

    @GetMapping("/readAllReservByStolId/{id}")
    public ResponseEntity<List<ReservDTO>> readAllReservByStolId(@PathVariable Long id) {
        return new ResponseEntity<>(reservService.getAllByStolId(id), HttpStatus.OK);
    }
}
