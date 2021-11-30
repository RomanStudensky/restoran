package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.OrdersDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Order;
import ru.pnzgu.restauran.store.repository.OrderRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    final OrderRepository orderRepository;
    final SimpleMapper<OrdersDTO, Order> simpleMapper = new SimpleMapper<>(new OrdersDTO(), new Order());

    public List<OrdersDTO> getAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public OrdersDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        orderRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Заявка с идентификатором - %s не найден", id))));
    }

    public OrdersDTO save(OrdersDTO dto) {
        return simpleMapper
                .mapEntityToDto(
                        orderRepository
                                .save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public OrdersDTO update(Long id, OrdersDTO dto) {
        orderRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Заявка с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        orderRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
