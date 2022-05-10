package ru.pnzgu.restauran.service;

import org.springframework.transaction.annotation.Transactional;
import ru.pnzgu.restauran.dto.SostavPostavDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.*;
import ru.pnzgu.restauran.store.repository.NakladRepository;
import ru.pnzgu.restauran.store.repository.ProductRepository;
import ru.pnzgu.restauran.store.repository.SostavPostavRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SostavPostavService {

    private final SostavPostavRepository sostavPostavRepository;
    private final NakladRepository nakladRepository;
    private final NakladService nakladService;
    private final ProductRepository productRepository;
    private final SimpleMapper<SostavPostavDTO, SostavPostav> simpleMapper = new SimpleMapper<>(new SostavPostavDTO(), new SostavPostav());

    @Transactional(readOnly = true)
    public List<SostavPostavDTO> getAllSostavBySostavNaklId(Long id) {
        return sostavPostavRepository
                .findAllByTovarNakladId(id)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SostavPostavDTO get(Long id) {
        return simpleMapper.mapEntityToDto(
                sostavPostavRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Поставка с идентификатором - %s не найдена", id))));

    }

    @Transactional
    public SostavPostavDTO save(Long nakladId, Long productId, SostavPostavDTO dto) {
        Naklad naklad = nakladRepository
                .findById(nakladId)
                .orElseThrow(() -> new NotFoundException(String.format("Накладная с идентификатором - %s не найдена", nakladId)));

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Продукт с идентификатором - %s не найден", productId)));

        SostavPostav sostavPostav = simpleMapper.mapDtoToEntity(dto);
        sostavPostav.setTovarNaklad(naklad);
        sostavPostav.setProduct(product);
        sostavPostav.setSumma(sostavPostav.getQuantity().doubleValue() * sostavPostav.getPrice().doubleValue());

        SostavPostavDTO sostavPostavDTO = simpleMapper
                .mapEntityToDto(
                        sostavPostavRepository
                                .save(sostavPostav)
                );

        nakladService.updateSumma(naklad.getId(), sostavPostavRepository
                .findAllByTovarNakladId(naklad.getId())
                .stream()
                .map(SostavPostav::getSumma)
                .reduce(0.0D, Double::sum));

        return sostavPostavDTO;
    }

    @Transactional
    public void delete(Long id) {
        SostavPostav sostavPostav = sostavPostavRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Состав с идентификатором - %s не найден", id)));
        sostavPostavRepository.delete(sostavPostav);
    }
}
