package ru.pnzgu.restauran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pnzgu.restauran.dto.CategoryDTO;
import ru.pnzgu.restauran.dto.MenuDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Category;
import ru.pnzgu.restauran.store.entity.Menu;
import ru.pnzgu.restauran.store.entity.Product;
import ru.pnzgu.restauran.store.repository.CategoryRepository;
import ru.pnzgu.restauran.store.repository.MenuRepository;
import ru.pnzgu.restauran.store.repository.ProductRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final SimpleMapper<MenuDTO, Menu> simpleMapper = new SimpleMapper<>(new MenuDTO(), new Menu());

    public List<MenuDTO> getAllByCategId(Long categId) {
        return menuRepository
                .findAllByCategory_Id(categId)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public Long getCategIdByMenuId(Long menuId) {
        return menuRepository.getById(menuId).getCategory().getId();
    }

    public List<MenuDTO> getAll() {
        return menuRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public MenuDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        menuRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Блюдо с идентификатором - %s не найдено", id))));
    }

    public MenuDTO save(MenuDTO menuDTO, Long categId) {
        Category category = categoryRepository
                .findById(categId)
                .orElseThrow(() -> new NotFoundException(String.format("Категория с идентификатором - %s не найдена", categId)));
        Menu menu = simpleMapper.mapDtoToEntity(menuDTO);
        menu.setCategory(category);

        return simpleMapper
                .mapEntityToDto(
                        menuRepository
                                .save(menu)
                );
    }

    public MenuDTO update(Long id, MenuDTO menuDTO) {

        get(id);

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
