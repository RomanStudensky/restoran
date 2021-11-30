package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.SostavZakazDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Menu;
import ru.pnzgu.restauran.store.entity.SostavZakaz;
import ru.pnzgu.restauran.store.entity.Zakaz;
import ru.pnzgu.restauran.store.repository.MenuRepository;
import ru.pnzgu.restauran.store.repository.SostavZakazRepository;
import ru.pnzgu.restauran.store.repository.ZakazRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SostavZakazService {

    private final SostavZakazRepository sostavZakazRepository;
    private final MenuRepository menuRepository;
    private final ZakazRepository zakazRepository;
    private final SimpleMapper<SostavZakazDTO, SostavZakaz> simpleMapper = new SimpleMapper<>(new SostavZakazDTO(), new SostavZakaz());

    public List<SostavZakazDTO> getAllSostavZakazByZakazId(Long id) {
        return sostavZakazRepository
                .findAllByZakazId(id)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public SostavZakazDTO getSostav(Long id) {
        return simpleMapper.mapEntityToDto(sostavZakazRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Состава заказа с идентификатором - %s не найден", id))));
    }

    public SostavZakazDTO save(Long zakazId, Long menuId, SostavZakazDTO dto) {
        Zakaz zakaz = zakazRepository
                .findById(zakazId)
                .orElseThrow(() -> new NotFoundException(String.format("Заказ с идентификатором - %s не найден", zakazId)));

        Menu menu = menuRepository
                .findById(menuId)
                .orElseThrow(() -> new NotFoundException(String.format("Блюдо с идентификатором - %s не найдено", menuId)));

        SostavZakaz sostavZakaz = simpleMapper.mapDtoToEntity(dto);
        sostavZakaz.setZakaz(zakaz);
        sostavZakaz.setBludo(menu);
        sostavZakaz.setSumma(BigDecimal.valueOf(dto.getQuantity() * menu.getPrice().longValue()));

        return simpleMapper
                .mapEntityToDto(
                        sostavZakazRepository
                                .save(sostavZakaz)
                );
    }

    public SostavZakazDTO update(Long id, SostavZakazDTO dto) {
        sostavZakazRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Состава заказа с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        sostavZakazRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        sostavZakazRepository.deleteById(id);
    }
}
