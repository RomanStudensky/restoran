package ru.pnzgu.restauran.service;

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


    public List<SostavPostavDTO> getAllSostavBySostavNaklId(Long id) {
        return sostavPostavRepository
                .findAllByTovarNakladId(id)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public SostavPostavDTO get(Long id) {
        return simpleMapper.mapEntityToDto(
                sostavPostavRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Поставка с идентификатором - %s не найден", id))));

    }

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

        nakladService.updateSumma(naklad.getId(), sostavPostavRepository
                .findAllByTovarNakladId(naklad.getId())
                .stream()
                .map(SostavPostav::getSumma)
                .reduce(0.0D, Double::sum));

        return simpleMapper
                .mapEntityToDto(
                        sostavPostavRepository
                                .save(sostavPostav)
                );
    }

    public void delete(Long id) {
        Long idNaklad = sostavPostavRepository.findById(id).get().getTovarNaklad().getId();

        sostavPostavRepository.deleteById(id);

        nakladService.updateSumma(idNaklad, sostavPostavRepository
                .findAllByTovarNakladId(idNaklad)
                .stream()
                .map(SostavPostav::getSumma)
                .reduce(0.0D, Double::sum));
    }
}
