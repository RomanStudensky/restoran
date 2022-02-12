package ru.pnzgu.restauran.service;

import ru.pnzgu.restauran.dto.NakladDTO;
import ru.pnzgu.restauran.dto.OrdersDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.*;
import ru.pnzgu.restauran.store.repository.DogovorRepository;
import ru.pnzgu.restauran.store.repository.OrderRepository;
import ru.pnzgu.restauran.store.repository.PostavshikRepository;
import ru.pnzgu.restauran.store.repository.SostavPostavRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final SostavPostavRepository sostavPostavRepository;
    private final PostavshikRepository postavshikRepository;
    private final DogovorRepository dogovorRepository;
    private final SimpleMapper<OrdersDTO, Order> simpleMapper = new SimpleMapper<>(new OrdersDTO(), new Order());

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

    public OrdersDTO save(OrdersDTO ordersDTO, Long postavshikId, Long dogovorId) {
        Order order = simpleMapper.mapDtoToEntity(ordersDTO);

        Postavshik postavshik = postavshikRepository
                .findById(postavshikId)
                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", postavshikId)));

        Dogovor dogovor = dogovorRepository
                .findById(dogovorId)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", dogovorId)));

        order.setId(null);
        order.setDogovor(dogovor);
        order.setPostavshik(postavshik);

        return simpleMapper
                .mapEntityToDto(
                        orderRepository
                                .save(order)
                );
    }

    public OrdersDTO update(Long id, OrdersDTO ordersDTO, Long postavshikId, Long dogovorId) {
        Order order = simpleMapper.mapDtoToEntity(ordersDTO);

        orderRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Накладная с идентификатором - %s не найдена", id)));

        Postavshik postavshik = postavshikRepository
                .findById(postavshikId)
                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", postavshikId)));

        Dogovor dogovor = dogovorRepository
                .findById(dogovorId)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", dogovorId)));

        order.setDogovor(dogovor);
        order.setPostavshik(postavshik);

        return simpleMapper
                .mapEntityToDto(
                        orderRepository
                                .save(order)
                );
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Long getFirstOrder() {
        return orderRepository.findMinIdOrder().orElse(0L);
    }
}
