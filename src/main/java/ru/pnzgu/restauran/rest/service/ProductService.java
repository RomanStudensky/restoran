package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.ProductDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Product;
import ru.pnzgu.restauran.store.repository.ProductRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository postavshikRepository;
    final SimpleMapper<ProductDTO, Product> simpleMapper = new SimpleMapper<>(new ProductDTO(), new Product());

    public List<ProductDTO> getAll() {
        return postavshikRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public ProductDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        postavshikRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", id))));
    }

    public ProductDTO save(ProductDTO dto) {
        return simpleMapper
                .mapEntityToDto(
                        postavshikRepository
                                .save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public ProductDTO update(Long id, ProductDTO dto) {
        postavshikRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        postavshikRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        postavshikRepository.deleteById(id);
    }
}
