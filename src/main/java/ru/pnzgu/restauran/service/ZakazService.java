package ru.pnzgu.restauran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pnzgu.restauran.dto.ZakazDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Stol;
import ru.pnzgu.restauran.store.entity.User;
import ru.pnzgu.restauran.store.entity.Zakaz;
import ru.pnzgu.restauran.store.repository.StolRepository;
import ru.pnzgu.restauran.store.repository.UserRepository;
import ru.pnzgu.restauran.store.repository.ZakazRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZakazService {

    final UserRepository sotrudnikRepository;
    final StolRepository stolRepository;
    final ZakazRepository zakazRepository;
    final SimpleMapper<ZakazDTO, Zakaz> simpleMapper = new SimpleMapper<>(new ZakazDTO(), new Zakaz());

    public List<ZakazDTO> getAll() {
        return zakazRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public ZakazDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        zakazRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", id))));

    }

    public ZakazDTO save(ZakazDTO zakazDTO, Long stolId, Long sotrudId) {

        Zakaz zakaz = simpleMapper.mapDtoToEntity(zakazDTO);

        Stol stol = stolRepository
                .findById(stolId)
                .orElseThrow(() -> new NotFoundException(String.format("Стол с идентификатором - %s не найден", stolId)));
        User sotrudnik = sotrudnikRepository
                .findById(sotrudId)
                .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", sotrudId)));

        zakaz.setSumma(BigDecimal.valueOf(0));
        zakaz.setStol(stol);
        zakaz.setSotrud(sotrudnik);

        return simpleMapper
                .mapEntityToDto(
                        zakazRepository
                                .save(zakaz)
                );
    }

    public ZakazDTO update(ZakazDTO zakazDTO, Long stolId, Long sotrudId, Long zakazId) {

         zakazRepository
                .findById(zakazId)
                .orElseThrow(() -> new NotFoundException(String.format("Заказ с идентификатором - %s не найден", stolId)));

        Zakaz zakaz = simpleMapper.mapDtoToEntity(zakazDTO);

        Stol stol = stolRepository
                .findById(stolId)
                .orElseThrow(() -> new NotFoundException(String.format("Стол с идентификатором - %s не найден", stolId)));
        User sotrudnik = sotrudnikRepository
                .findById(sotrudId)
                .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", sotrudId)));

        zakaz.setSumma(BigDecimal.valueOf(0));
        zakaz.setStol(stol);
        zakaz.setSotrud(sotrudnik);

        return simpleMapper
                .mapEntityToDto(
                        zakazRepository
                                .save(zakaz)
                );
    }

    public void delete(Long id) {
        zakazRepository.deleteById(id);
    }

    public Long getFirstZakaz() {
        return zakazRepository.findMinIdZakaz().orElse(0L);
    }
}
