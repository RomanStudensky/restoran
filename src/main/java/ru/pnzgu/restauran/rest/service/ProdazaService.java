package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.ProdazaDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Prodaza;
import ru.pnzgu.restauran.store.repository.ProdazaRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  ProdazaService {

    final ProdazaRepository prodazaRepository;
    final SimpleMapper<ProdazaDTO, Prodaza> simpleMapper = new SimpleMapper<>(new ProdazaDTO(), new Prodaza());

    public List<ProdazaDTO> getAll() {
        return prodazaRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public ProdazaDTO getProdaza(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        prodazaRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Продажа с идентификатором - %s не найден", id))));

    }

    public ProdazaDTO save(ProdazaDTO prodazaDTO) {
        return simpleMapper
                .mapEntityToDto(
                        prodazaRepository
                                .save(simpleMapper.mapDtoToEntity(prodazaDTO))
                );
    }

    public ProdazaDTO update(Long id, ProdazaDTO prodazaDTO) {
        prodazaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Продажа с идентификатором - %s не найден", id)));

        prodazaDTO.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        prodazaRepository.save(simpleMapper.mapDtoToEntity(prodazaDTO))
                );
    }

    public void delete(Long id) {
        prodazaRepository.deleteById(id);
    }
}
