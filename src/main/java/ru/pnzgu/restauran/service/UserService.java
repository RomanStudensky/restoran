package ru.pnzgu.restauran.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.pnzgu.restauran.dto.UserDto;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.User;
import ru.pnzgu.restauran.store.repository.UserRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SimpleMapper<UserDto, User> simpleMapper = new SimpleMapper<>(new UserDto(), new User());
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public List<UserDto> getAll() {
        List<UserDto> users = userRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
        users.forEach(user -> user.setPassword(""));
        return users;
    }

    public UserDto get(Long id) {
        UserDto userDto = simpleMapper
                .mapEntityToDto(
                        userRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", id))));
        userDto.setPassword("");
        return userDto;
    }

    public UserDto save(UserDto sotrudnikDTO) {
        sotrudnikDTO.setPassword(passwordEncoder.encode(sotrudnikDTO.getPassword()));
        return simpleMapper
                .mapEntityToDto(
                        userRepository
                                .save(simpleMapper.mapDtoToEntity(sotrudnikDTO))
                );
    }



    public UserDto update(Long id, UserDto sotrudnikDTO) {
        userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", id)));

        sotrudnikDTO.setId(id);
        sotrudnikDTO.setPassword(passwordEncoder.encode(sotrudnikDTO.getPassword()));

        return simpleMapper
                .mapEntityToDto(
                        userRepository.save(simpleMapper.mapDtoToEntity(sotrudnikDTO))
                );
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto getByUsername(String username) {
        return simpleMapper
                .mapEntityToDto(
                        userRepository
                                .findByUsername(username)
                                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с именем - %s не найден", username)))
                );
    }
}
