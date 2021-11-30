package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.SostavProdDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.SostavProd;
import ru.pnzgu.restauran.store.repository.SostavProdRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SostavProdService {

    final SostavProdRepository sostavProdRepository;
    final SimpleMapper<SostavProdDTO, SostavProd> simpleMapper = new SimpleMapper<>(new SostavProdDTO(), new SostavProd());


    public List<SostavProdDTO> getAllSostavByProdazaId(Long id) {
        return sostavProdRepository
                .findByProdazaId(id)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public SostavProdDTO getSostav(Long id) {
        return simpleMapper.mapEntityToDto(sostavProdRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id))));
    }

    public SostavProdDTO save(SostavProdDTO dto) {
        return simpleMapper
                .mapEntityToDto(
                        sostavProdRepository
                                .save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public SostavProdDTO update(Long id, SostavProdDTO dto) {
        sostavProdRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        sostavProdRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        sostavProdRepository.deleteById(id);
    }
}
