package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.AktDTO;
import ru.pnzgu.restauran.dto.ProductDTO;
import ru.pnzgu.restauran.dto.SpisProductDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.SpisProduct;
import ru.pnzgu.restauran.store.repository.SpisProductRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpisProductService {

    final SpisProductRepository spisProductRepository;
    final AktService aktService;
    final ProductService productService;
    final SimpleMapper<SpisProductDTO, SpisProduct> simpleMapper = new SimpleMapper<>(new SpisProductDTO(), new SpisProduct());


    public SpisProductDTO get(Long id) {
        return simpleMapper.mapEntityToDto(spisProductRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id))));
    }

    @Transactional
    public SpisProductDTO save(SpisProductDTO dto) throws Exception {

        ProductDTO product = productService
                .get(dto.getProduct().getId());

        AktDTO aktSpis = aktService
                .get(dto.getAktSpis().getId());

        if (product.getQuantity() < dto.getQuantity()) {
            throw new Exception(String.format("Товара %s на складе недостаточно для списания", product.getNameProd()));
        }
        product.setQuantity(product.getQuantity() - dto.getQuantity());
        productService.save(product);

        dto.setProduct(product);
        dto.setAktSpis(aktSpis);

        SpisProductDTO newSpis = simpleMapper.mapEntityToDto(spisProductRepository.save(simpleMapper.mapDtoToEntity(dto)));

        aktSpis.getSpisProducts().add(newSpis);
        aktService.update(dto.getAktSpis().getId(), aktSpis);


        return newSpis;
    }

    public SpisProductDTO update(Long id, SpisProductDTO dto) {
        spisProductRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        spisProductRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        spisProductRepository.deleteById(id);
    }
}
