package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.SostavPostavDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.SostavPostav;
import ru.pnzgu.restauran.store.repository.SostavPostavRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SostavPostavService {

    final SostavPostavRepository sostavPostavRepository;
    final SimpleMapper<SostavPostavDTO, SostavPostav> simpleMapper = new SimpleMapper<>(new SostavPostavDTO(), new SostavPostav());


    public List<SostavPostavDTO> getAllSostavBySostavNaklId(Long id) {
        return sostavPostavRepository
                .findAllByTovarNakladId(id)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public SostavPostavDTO get(Long id) {
        return simpleMapper.mapEntityToDto(
                sostavPostavRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id))));

    }

    public SostavPostavDTO save(SostavPostavDTO dto) {
        return simpleMapper
                .mapEntityToDto(
                        sostavPostavRepository
                                .save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public SostavPostavDTO update(Long id, SostavPostavDTO dto) {
        sostavPostavRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        sostavPostavRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        sostavPostavRepository.deleteById(id);
    }
}
