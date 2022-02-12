package ru.pnzgu.restauran.rest.controller.povar.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.ProductDTO;
import ru.pnzgu.restauran.rest.service.povar.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/povar/product")
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;

    @GetMapping("/readAll")
    public ResponseEntity<List<ProductDTO>> readAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ProductDTO> read(@PathVariable Long id) {
        return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.save(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.update(id, productDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }
}
