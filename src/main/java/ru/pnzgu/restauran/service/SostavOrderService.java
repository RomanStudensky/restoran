package ru.pnzgu.restauran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pnzgu.restauran.dto.SostavOrderDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Order;
import ru.pnzgu.restauran.store.entity.Product;
import ru.pnzgu.restauran.store.entity.SostavOrder;
import ru.pnzgu.restauran.store.repository.OrderRepository;
import ru.pnzgu.restauran.store.repository.ProductRepository;
import ru.pnzgu.restauran.store.repository.SostavOrderRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SostavOrderService {

    private final SostavOrderRepository sostavOrderRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private
    final SimpleMapper<SostavOrderDTO, SostavOrder> simpleMapper = new SimpleMapper<>(new SostavOrderDTO(), new SostavOrder());

    public List<SostavOrderDTO> getAllByOrderId(Long id) {
        return sostavOrderRepository
                .findAllByOrdersId(id)
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public SostavOrderDTO get(Long id) {
        return simpleMapper.mapEntityToDto(
                sostavOrderRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Состав заявки с идентификатором - %s не найден", id))));
    }

    public SostavOrderDTO save(SostavOrderDTO dto, Long productId, Long orderId) {

        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new NotFoundException(String.format("Заявка с идентификатором - %s не найдена", orderId)));

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Продукт с идентификатором - %s не найден", productId)));

        SostavOrder sostavOrder = simpleMapper.mapDtoToEntity(dto);
        sostavOrder.setOrders(order);
        sostavOrder.setProduct(product);

        return simpleMapper
                .mapEntityToDto(
                        sostavOrderRepository
                                .save(sostavOrder)
                );
    }

    public SostavOrderDTO update(Long sostavId, SostavOrderDTO dto, Long productId, Long orderId) {

        sostavOrderRepository
                .findById(sostavId)
                .orElseThrow(() -> new NotFoundException(String.format("Состав заявки с идентификатором - %s не найден", sostavId)));

        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new NotFoundException(String.format("Заявка с идентификатором - %s не найдена", orderId)));

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Продукт с идентификатором - %s не найден", productId)));

        SostavOrder sostavOrder = simpleMapper.mapDtoToEntity(dto);
        sostavOrder.setId(sostavId);
        sostavOrder.setOrders(order);
        sostavOrder.setProduct(product);

        return simpleMapper
                .mapEntityToDto(
                        sostavOrderRepository
                                .save(sostavOrder)
                );
    }

    public void delete(Long id) {
        sostavOrderRepository.deleteById(id);
    }
}
