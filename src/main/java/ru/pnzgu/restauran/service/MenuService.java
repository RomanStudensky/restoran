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
import ru.pnzgu.restauran.util.mapping.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    

    public List<MenuDTO> getAllByCategId(Long categId) {
        return menuRepository
                .findAllByCategory_Id(categId)
                .stream()
                .map(Mappers.MENU::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategByMenuId(Long menuId) {
        return Mappers.CATEGORY.mapEntityToDto(menuRepository.getById(menuId).getCategory());
    }

    public List<MenuDTO> getAll() {
        return menuRepository
                .findAll()
                .stream()
                .map(Mappers.MENU::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public MenuDTO get(Long id) {
        return Mappers.MENU
                .mapEntityToDto(
                        menuRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Блюдо с идентификатором - %s не найдено", id))));
    }

    public MenuDTO save(MenuDTO menuDTO, Long categId) {
        Category category = categoryRepository
                .findById(categId)
                .orElseThrow(() -> new NotFoundException(String.format("Категория с идентификатором - %s не найдена", categId)));
        Menu menu = Mappers.MENU.mapDtoToEntity(menuDTO);
        menu.setCategory(category);

        return Mappers.MENU
                .mapEntityToDto(
                        menuRepository
                                .save(menu)
                );
    }

    public MenuDTO update(Long id, MenuDTO menuDTO, Long categId) {

        get(id);

        Category category = categoryRepository
                .findById(categId)
                .orElseThrow(() -> new NotFoundException(String.format("Категория с идентификатором - %s не найдена", categId)));

        menuDTO.setId(id);
        Menu menu = Mappers.MENU.mapDtoToEntity(menuDTO);
        menu.setCategory(category);


        return Mappers.MENU
                .mapEntityToDto(
                        menuRepository.save(menu)
                );
    }

    public void delete(Long id) {
        menuRepository.deleteById(id);
    }
}
