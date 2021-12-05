package ru.pnzgu.restauran.service;

import ru.pnzgu.restauran.dto.AktDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.AktSpis;
import ru.pnzgu.restauran.store.entity.Sotrudnik;
import ru.pnzgu.restauran.store.repository.AktRepository;
import ru.pnzgu.restauran.store.repository.SotrudnikRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AktService {

    final AktRepository aktRepository;
    final SotrudnikRepository sotrudnikRepository;
    final SimpleMapper<AktDTO, AktSpis> simpleMapper = new SimpleMapper<>(new AktDTO(), new AktSpis());

    public List<AktDTO> getAll() {
        return aktRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }



    public AktDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        aktRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Акт списания с идентификатором - %s не найден", id))));
    }

    public AktDTO save(AktDTO dto) {

        Sotrudnik sotrudnik =
                sotrudnikRepository
                        .findById(dto.getSotrud().getId())
                        .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", dto.getSotrud().getId())));

        return simpleMapper
                .mapEntityToDto(
                        aktRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public AktDTO update(Long id, AktDTO dto) {
        aktRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Акт списания с идентификатором - %s не найден", id)));

        dto.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        aktRepository.save(simpleMapper.mapDtoToEntity(dto))
                );
    }

    public void delete(Long id) {
        aktRepository.deleteById(id);
    }

}
