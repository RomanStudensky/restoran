package ru.pnzgu.restauran.rest.controller.director;

import ru.pnzgu.restauran.dto.DogovorDTO;
import ru.pnzgu.restauran.dto.PostavshikDTO;
import ru.pnzgu.restauran.rest.service.director.DogovorService;
import ru.pnzgu.restauran.rest.service.director.PostavshikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/director")
@RequiredArgsConstructor
public class DirectorController {

    final PostavshikService postavshikService;
    final DogovorService dogovorService;

    @GetMapping("/postavshik/readAll")
    public ResponseEntity<List<PostavshikDTO>> readAllPostavshik() {
        return new ResponseEntity<>(postavshikService.getAllPostavshik(), HttpStatus.OK);
    }

    @GetMapping("/postavshik/read/{id}")
    public ResponseEntity<PostavshikDTO> readPostavshik(@PathVariable Long id) {
        return new ResponseEntity<>(postavshikService.get(id), HttpStatus.OK);
    }

    @PostMapping("/postavshik/create")
    public ResponseEntity<PostavshikDTO> addPostavshik(@RequestBody PostavshikDTO postavshikDTO) {
        return new ResponseEntity<>(postavshikService.save(postavshikDTO), HttpStatus.CREATED);
    }

    @PutMapping("/postavshik/update/{id}")
    public ResponseEntity<PostavshikDTO> updatePostavshik(@PathVariable Long id, @RequestBody PostavshikDTO postavshikDTO) {
        return new ResponseEntity<>(postavshikService.update(id, postavshikDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/postavshik/delete/{id}")
    public HttpStatus deletePostavshik(@PathVariable Long id) {
        postavshikService.delete(id);
        return HttpStatus.OK;
    }

    // Договоры
    @GetMapping("/dogovor/readAllByPostavshikId/{id}")
    public ResponseEntity<List<DogovorDTO>> readAllDogovorByPostavshikId(@PathVariable Long id) {
        return new ResponseEntity<>(dogovorService.getAllByPostavshikId(id), HttpStatus.OK);
    }

    @GetMapping("/dogovor/read/{id}")
    public ResponseEntity<DogovorDTO> readDogovor(@PathVariable Long id) {
        return new ResponseEntity<>(dogovorService.get(id), HttpStatus.OK);
    }

    @PostMapping("/dogovor/create")
    public ResponseEntity<DogovorDTO> addDogovor(@RequestBody DogovorDTO dto) {
        return new ResponseEntity<>(dogovorService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/dogovor/update/{id}")
    public ResponseEntity<DogovorDTO> updateDogovor(@PathVariable Long id, @RequestBody DogovorDTO dto) {
        return new ResponseEntity<>(dogovorService.update(id, dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/dogovor/delete/{id}")
    public HttpStatus deleteDogovor(@PathVariable Long id) {
        dogovorService.delete(id);
        return HttpStatus.OK;
    }

}
