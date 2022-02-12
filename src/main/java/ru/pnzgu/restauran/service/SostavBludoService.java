package ru.pnzgu.restauran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pnzgu.restauran.dto.SostavBludoDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Menu;
import ru.pnzgu.restauran.store.entity.Product;
import ru.pnzgu.restauran.store.entity.SostavBludo;
import ru.pnzgu.restauran.store.repository.MenuRepository;
import ru.pnzgu.restauran.store.repository.ProductRepository;
import ru.pnzgu.restauran.store.repository.SostavBludoRepository;
import ru.pnzgu.restauran.util.mapping.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SostavBludoService {

    private final SostavBludoRepository sostavBludoRepository;
    private final ProductRepository productRepository;
    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public List<SostavBludoDTO> getAllSostavByMenuId(Long id) {
        return sostavBludoRepository
                .findAllByMenu_Id(id)
                .stream()
                .map(Mappers.SOSTAV_BLUDO::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SostavBludoDTO> getAll() {
        return sostavBludoRepository
                .findAll()
                .stream()
                .map(Mappers.SOSTAV_BLUDO::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SostavBludoDTO get(Long id) {
        return Mappers.SOSTAV_BLUDO.mapEntityToDto(
                sostavBludoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Состав с идентификатором - %s не найдена", id))));

    }

    @Transactional
    public SostavBludoDTO save(SostavBludoDTO dto, Long menuId, Long productId) {
        Menu menu = menuRepository
            .findById(menuId)
            .orElseThrow(() -> new NotFoundException(String.format("Блюдо с идентификатором - %s не найдено", menuId)));

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Продукт с идентификатором - %s не найден", productId)));

        SostavBludo sostavBludo = Mappers.SOSTAV_BLUDO.mapDtoToEntity(dto);
        sostavBludo.setProduct(product);
        sostavBludo.setMenu(menu);


        return Mappers.SOSTAV_BLUDO
                .mapEntityToDto(
                        sostavBludoRepository
                                .save(sostavBludo)
                );
    }

    @Transactional
    public SostavBludoDTO update(Long sostavId, SostavBludoDTO dto, Long productId) {

        sostavBludoRepository
                .findById(sostavId)
                .orElseThrow(() -> new NotFoundException(String.format("Состав с идентификатором - %s не найден", sostavId)));

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Продукт с идентификатором - %s не найден", productId)));


        Menu menu = sostavBludoRepository
                .findById(sostavId)
                .orElseThrow(() -> new NotFoundException(String.format("Блюдо с идентификатором - %s не найдено", productId)))
                .getMenu();

        SostavBludo sostavBludo = Mappers.SOSTAV_BLUDO.mapDtoToEntity(dto);
        sostavBludo.setId(sostavId);
        sostavBludo.setProduct(product);
        sostavBludo.setMenu(menu);

        SostavBludoDTO sostavBludoDTO = Mappers.SOSTAV_BLUDO
                .mapEntityToDto(
                        sostavBludoRepository
                                .save(sostavBludo)
                );

        sostavBludoDTO.setMenu(Mappers.MENU.mapEntityToDto(menu));

        return sostavBludoDTO;
    }

    public void delete(Long id) {
        sostavBludoRepository.deleteById(id);
    }

    public Long getMenuIdBySostavId(Long id) {
        return sostavBludoRepository.getById(id).getMenu().getId();
    }
}
