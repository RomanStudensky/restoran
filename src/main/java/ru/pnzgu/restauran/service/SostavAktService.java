package ru.pnzgu.restauran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pnzgu.restauran.dto.AktDTO;
import ru.pnzgu.restauran.dto.ProductDTO;
import ru.pnzgu.restauran.dto.SostavAktDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.SostavAkt;
import ru.pnzgu.restauran.store.repository.SostavAktRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SostavAktService {

    private final SostavAktRepository sostavAktRepository;
    private final AktService aktService;
    private final ProductService productService;
    private final SimpleMapper<SostavAktDTO, SostavAkt> simpleMapper = new SimpleMapper<>(new SostavAktDTO(), new SostavAkt());


    public SostavAktDTO get(Long id) {
        return simpleMapper.mapEntityToDto(sostavAktRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Состав акта с идентификатором - %s не найден", id))));
    }

    @Transactional
    public SostavAktDTO save(Long aktId, Long productId, SostavAktDTO dto) {

        ProductDTO product = productService.get(productId);

        AktDTO aktSpis = aktService.get(aktId);

        if (product.getQuantity() < dto.getQuantity()) {
            throw new NotFoundException(String.format("Товара %s на складе недостаточно для списания. Доступное количество - %s.", product.getNameProd(), product.getQuantity()));
        }
        product.setQuantity(product.getQuantity() - dto.getQuantity());
        productService.save(product);

        dto.setProduct(product);
        dto.setAktSpis(aktSpis);

        SostavAktDTO newSpis = simpleMapper
                .mapEntityToDto(
                        sostavAktRepository
                                .save(simpleMapper
                                        .mapDtoToEntity(dto)
                                )
                );

        aktService.setSumma(aktId);
        return newSpis;
    }

    public SostavAktDTO update(Long id, SostavAktDTO dto) {
        sostavAktRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        sostavAktRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        sostavAktRepository.deleteById(id);
    }

    public List<SostavAktDTO> getSostavByAktId(Long id) {
        List<SostavAktDTO> list = sostavAktRepository
                .findSostavAktByAktSpis_Id(id)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
        list.forEach(dto -> dto.setSumma(dto.getPrice() * dto.getQuantity()));
        return list;
    }
}
