package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.DogovorDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Dogovor;
import ru.pnzgu.restauran.store.repository.DogovorRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogovorService {

    final DogovorRepository dogovorRepository;
    final SimpleMapper<DogovorDTO, Dogovor> simpleMapper = new SimpleMapper<>(new DogovorDTO(), new Dogovor());

    public List<DogovorDTO> getAllByPostavshikId(Long id) {
        return dogovorRepository
                .findDogovorsByPostavshikId(id)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public DogovorDTO get(Long id) {
        return simpleMapper.mapEntityToDto(dogovorRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id))));
    }

    public DogovorDTO save(DogovorDTO dto) {
        return simpleMapper
                .mapEntityToDto(
                        dogovorRepository
                                .save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public DogovorDTO update(Long id, DogovorDTO dto) {
        dogovorRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        dogovorRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        dogovorRepository.deleteById(id);
    }
}
