package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.PostavshikDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Postavshik;
import ru.pnzgu.restauran.store.repository.PostavshikRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostavshikService {

    final PostavshikRepository postavshikRepository;
    final SimpleMapper<PostavshikDTO, Postavshik> simpleMapper = new SimpleMapper<>(new PostavshikDTO(), new Postavshik());

    public List<PostavshikDTO> getAllPostavshik() {
        return postavshikRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public PostavshikDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        postavshikRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", id))));
    }

    public PostavshikDTO save(PostavshikDTO postavshikDTO) {
        return simpleMapper
                .mapEntityToDto(
                        postavshikRepository
                                .save(simpleMapper.mapDtoToEntity(postavshikDTO))
                );
    }



    public PostavshikDTO update(Long id, PostavshikDTO postavshikDTO) {
        postavshikRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", id)));

        postavshikDTO.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        postavshikRepository.save(simpleMapper.mapDtoToEntity(postavshikDTO))
                );
    }

    public void delete(Long id) {
        postavshikRepository.deleteById(id);
    }
}
