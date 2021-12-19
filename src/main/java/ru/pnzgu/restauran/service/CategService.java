package ru.pnzgu.restauran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pnzgu.restauran.dto.CategoryDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Category;
import ru.pnzgu.restauran.store.repository.CategoryRepository;
import ru.pnzgu.restauran.store.repository.MenuRepository;
import ru.pnzgu.restauran.util.mapping.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategService {
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;

    public List<CategoryDTO> getAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(Mappers.CATEGORY::mapEntityToDto)
                .collect(Collectors.toList());
    }



    public CategoryDTO get(Long id) {
        return Mappers.CATEGORY
                .mapEntityToDto(
                        categoryRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Категория с идентификатором - %s не найдена", id))));
    }

    public CategoryDTO save(CategoryDTO dto) {

        Category category = Mappers.CATEGORY.mapDtoToEntity(dto);
        category.setId(null);

        return Mappers.CATEGORY
                .mapEntityToDto(
                        categoryRepository.save(category)
                );
    }

    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category Category = Mappers.CATEGORY.mapDtoToEntity(dto);
        Category.setId(id);

        return Mappers.CATEGORY
                .mapEntityToDto(
                        categoryRepository.save(Category)
                );
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Long getFirstAkt() {
        return categoryRepository.findMinIdCategory().orElse(0L);
    }

}
