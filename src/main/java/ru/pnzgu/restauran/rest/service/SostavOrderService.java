package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.SostavOrderDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.SostavOrder;
import ru.pnzgu.restauran.store.repository.SostavOrderRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SostavOrderService {

    final SostavOrderRepository sostavPostavRepository;
    final SimpleMapper<SostavOrderDTO, SostavOrder> simpleMapper = new SimpleMapper<>(new SostavOrderDTO(), new SostavOrder());

    public List<SostavOrderDTO> getAllByOrderId(Long id) {
        return sostavPostavRepository
                .findAllByOrdersId(id)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public SostavOrderDTO get(Long id) {
        return simpleMapper.mapEntityToDto(
                sostavPostavRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Состав заявки с идентификатором - %s не найден", id))));
    }

    public SostavOrderDTO save(SostavOrderDTO dto) {
        return simpleMapper
                .mapEntityToDto(
                        sostavPostavRepository
                                .save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public SostavOrderDTO update(Long id, SostavOrderDTO dto) {
        sostavPostavRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Состав заявки с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        sostavPostavRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        sostavPostavRepository.deleteById(id);
    }
}
