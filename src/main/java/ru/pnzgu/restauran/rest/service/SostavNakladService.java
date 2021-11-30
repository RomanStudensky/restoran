package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.SostavNakladDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.SostavNaklad;
import ru.pnzgu.restauran.store.repository.SostavNakladRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SostavNakladService {

    final SostavNakladRepository sostavNakladRepository;
    final SimpleMapper<SostavNakladDTO, SostavNaklad> simpleMapper = new SimpleMapper<>(new SostavNakladDTO(), new SostavNaklad());

    public List<SostavNakladDTO> getAll() {
        return sostavNakladRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public SostavNakladDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        sostavNakladRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", id))));
    }

    public SostavNakladDTO save(SostavNakladDTO sostavNakladDTO) {
        return simpleMapper
                .mapEntityToDto(
                        sostavNakladRepository
                                .save(simpleMapper.mapDtoToEntity(sostavNakladDTO))
                );
    }



    public SostavNakladDTO update(Long id, SostavNakladDTO sostavNakladDTO) {
        sostavNakladRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", id)));

        sostavNakladDTO.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        sostavNakladRepository.save(simpleMapper.mapDtoToEntity(sostavNakladDTO))
                );
    }

    public void delete(Long id) {
        sostavNakladRepository.deleteById(id);
    }

}
