package ru.pnzgu.restauran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pnzgu.restauran.dto.SotrudnikDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.User;
import ru.pnzgu.restauran.store.repository.UserRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SotrudnikService {

    final UserRepository sotrudnikRepository;
    final SimpleMapper<SotrudnikDTO, User> simpleMapper = new SimpleMapper<>(new SotrudnikDTO(), new User());

    public List<SotrudnikDTO> getAll() {
        return sotrudnikRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public SotrudnikDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        sotrudnikRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", id))));
    }

    public SotrudnikDTO save(SotrudnikDTO sotrudnikDTO) {
        return simpleMapper
                .mapEntityToDto(
                        sotrudnikRepository
                                .save(simpleMapper.mapDtoToEntity(sotrudnikDTO))
                );
    }



    public SotrudnikDTO update(Long id, SotrudnikDTO sotrudnikDTO) {
        sotrudnikRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", id)));

        sotrudnikDTO.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        sotrudnikRepository.save(simpleMapper.mapDtoToEntity(sotrudnikDTO))
                );
    }

    public void delete(Long id) {
        sotrudnikRepository.deleteById(id);
    }
}
