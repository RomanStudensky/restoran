package ru.pnzgu.restauran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pnzgu.restauran.dto.ProdazaDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Prodaza;
import ru.pnzgu.restauran.store.entity.User;
import ru.pnzgu.restauran.store.repository.ProdazaRepository;
import ru.pnzgu.restauran.store.repository.UserRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  ProdazaService {

    private final UserRepository sotrudnikRepository;
    private final ProdazaRepository prodazaRepository;
    private final SimpleMapper<ProdazaDTO, Prodaza> simpleMapper = new SimpleMapper<>(new ProdazaDTO(), new Prodaza());

    public List<ProdazaDTO> getAll() {
        return prodazaRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public ProdazaDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        prodazaRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Продажа с идентификатором - %s не найдена", id))));

    }

    public ProdazaDTO save(ProdazaDTO prodazaDTO, Long sotrudId) {

        Prodaza prodaza = simpleMapper.mapDtoToEntity(prodazaDTO);

        User sotrudnik = sotrudnikRepository
                .findById(sotrudId)
                .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", sotrudId)));


        prodaza.setSumma(BigDecimal.valueOf(0));
        prodaza.setSotrud(sotrudnik);

        return simpleMapper
                .mapEntityToDto(
                        prodazaRepository
                                .save(prodaza)
                );
    }

    public ProdazaDTO update(Long id, ProdazaDTO prodazaDTO, Long sotrudId) {
        prodazaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Продажа с идентификатором - %s не найдена", id)));

        User sotrudnik = sotrudnikRepository
                .findById(sotrudId)
                .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", sotrudId)));

        Prodaza prodaza = simpleMapper.mapDtoToEntity(prodazaDTO);
        prodaza.setId(id);
        prodaza.setSotrud(sotrudnik);

        return simpleMapper
                .mapEntityToDto(
                        prodazaRepository.save(prodaza)
                );
    }

    public void delete(Long id) {
        prodazaRepository.deleteById(id);
    }

    public Long getFirstProdaza() {
        return prodazaRepository.getFirstProdaza().orElse(0L);
    }
}