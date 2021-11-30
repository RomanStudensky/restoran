package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.StolDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Reserv;
import ru.pnzgu.restauran.store.entity.Stol;
import ru.pnzgu.restauran.store.repository.StolRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StolService {

    final StolRepository stolRepository;
    final SimpleMapper<StolDTO, Stol> simpleMapper = new SimpleMapper<>(new StolDTO(), new Stol());

    public Long getFirstStol() {
        return stolRepository.findMinIdStol().stream().findFirst().orElse(-1L);
    }

    public List<StolDTO> getAllStol() {
        List<StolDTO> list = stolRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
        return list;
    }

    public StolDTO getStol(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        stolRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Стол с идентификатором - %s не найден", id))));
    }

    public StolDTO save(StolDTO stolDTO) {
        return simpleMapper
                .mapEntityToDto(
                        stolRepository
                                .save(simpleMapper.mapDtoToEntity(stolDTO))
                );
    }

    public StolDTO update(Long id, StolDTO stolDTO) {
        stolRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Стол с идентификатором - %s не найден", id)));

        stolDTO.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        stolRepository.save(simpleMapper.mapDtoToEntity(stolDTO))
                );
    }

    public void delete(Long id) {
        stolRepository.deleteById(id);
    }
}
