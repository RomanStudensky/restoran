package ru.pnzgu.restauran.service;

import ru.pnzgu.restauran.dto.SostavProdDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.*;
import ru.pnzgu.restauran.store.repository.MenuRepository;
import ru.pnzgu.restauran.store.repository.ProdazaRepository;
import ru.pnzgu.restauran.store.repository.SostavProdRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SostavProdService {

    private final SostavProdRepository sostavProdRepository;
    private final ProdazaRepository prodazaRepository;
    private final MenuRepository menuRepository;
    private final SimpleMapper<SostavProdDTO, SostavProd> simpleMapper = new SimpleMapper<>(new SostavProdDTO(), new SostavProd());


    public List<SostavProdDTO> getAllSostavByProdazaId(Long id) {
        return sostavProdRepository
                .findByProdazaId(id)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public SostavProdDTO getSostav(Long id) {
        return simpleMapper.mapEntityToDto(sostavProdRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id))));
    }

    public SostavProdDTO save(SostavProdDTO dto, Long zakazId, Long menuId) {
        Prodaza prodaza = prodazaRepository
                .findById(zakazId)
                .orElseThrow(() -> new NotFoundException(String.format("Заказ с идентификатором - %s не найден", zakazId)));

        Menu menu = menuRepository
                .findById(menuId)
                .orElseThrow(() -> new NotFoundException(String.format("Блюдо с идентификатором - %s не найдено", menuId)));

        SostavProd sostavProd = simpleMapper.mapDtoToEntity(dto);
        sostavProd.setProdaza(prodaza);
        sostavProd.setBludo(menu);
        sostavProd.setSumma(BigDecimal.valueOf(dto.getQuantity() * menu.getPrice().longValue()));

        return simpleMapper
                .mapEntityToDto(
                        sostavProdRepository
                                .save(sostavProd)
                );
    }

    public SostavProdDTO update(Long id, SostavProdDTO dto) {
        sostavProdRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        sostavProdRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        sostavProdRepository.deleteById(id);
    }
}
