package ru.pnzgu.restauran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pnzgu.restauran.dto.UserDTO;
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
    final SimpleMapper<UserDTO, User> simpleMapper = new SimpleMapper<>(new UserDTO(), new User());

    public List<UserDTO> getAll() {
        return sotrudnikRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public UserDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        sotrudnikRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", id))));
    }

    public UserDTO save(UserDTO sotrudnikDTO) {
        return simpleMapper
                .mapEntityToDto(
                        sotrudnikRepository
                                .save(simpleMapper.mapDtoToEntity(sotrudnikDTO))
                );
    }



    public UserDTO update(Long id, UserDTO sotrudnikDTO) {
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
