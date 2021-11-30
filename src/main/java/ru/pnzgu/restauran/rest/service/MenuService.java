package ru.pnzgu.restauran.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pnzgu.restauran.dto.MenuDTO;
import ru.pnzgu.restauran.dto.StolDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Menu;
import ru.pnzgu.restauran.store.repository.MenuRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final SimpleMapper<MenuDTO, Menu> simpleMapper = new SimpleMapper<>(new MenuDTO(), new Menu());

    public List<MenuDTO> getAll() {
        return menuRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public MenuDTO getById(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        menuRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Блюдо с идентификатором - %s не найдено", id))));
    }

    public MenuDTO save(MenuDTO menuDTO) {
        return simpleMapper
                .mapEntityToDto(
                        menuRepository
                                .save(simpleMapper.mapDtoToEntity(menuDTO))
                );
    }

    public MenuDTO update(Long id, MenuDTO menuDTO) {

        getById(id);

        menuDTO.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        menuRepository.save(simpleMapper.mapDtoToEntity(menuDTO))
                );
    }

    public void delete(Long id) {
        menuRepository.deleteById(id);
    }
}
