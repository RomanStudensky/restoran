package ru.pnzgu.restauran.service;

import ru.pnzgu.restauran.dto.AktDTO;
import ru.pnzgu.restauran.dto.SostavAktDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.AktSpis;
import ru.pnzgu.restauran.store.entity.SostavAkt;
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

    private final AktRepository aktRepository;
    private final SotrudnikRepository sotrudnikRepository;
    private final SostavAktService sostavAktService;
    private final SimpleMapper<AktDTO, AktSpis> simpleMapper = new SimpleMapper<>(new AktDTO(), new AktSpis());

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

    public AktDTO save(AktDTO dto, Long sotrudnikId) {

        Sotrudnik sotrudnik =
                sotrudnikRepository
                        .findById(sotrudnikId)
                        .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", sotrudnikId)));

        AktSpis aktSpis = simpleMapper.mapDtoToEntity(dto);
        aktSpis.setSotrud(sotrudnik);

        return simpleMapper
                .mapEntityToDto(
                        aktRepository.save(aktSpis)
                );
    }

    public AktDTO update(Long id, AktDTO dto, Long sotrudnikId) {
        aktRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Акт списания с идентификатором - %s не найден", id)));

        Sotrudnik sotrudnik =
                sotrudnikRepository
                        .findById(sotrudnikId)
                        .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с идентификатором - %s не найден", sotrudnikId)));

        AktSpis aktSpis = simpleMapper.mapDtoToEntity(dto);
        aktSpis.setSotrud(sotrudnik);
        aktSpis.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        aktRepository.save(aktSpis)
                );
    }

    public void delete(Long id) {
        aktRepository.deleteById(id);
    }

    public Long getFirstAkt() {
        return aktRepository.findMinIdAkt().orElse(0L);
    }

    public void setSumma(Long aktId) {
        AktDTO aktDTO = get(aktId);

        aktDTO.setSumma(sostavAktService
                .getSostavByAktId(aktId)
                .stream()
                .map(SostavAktDTO::getSumma)
                .reduce(0.0, Double::sum));

        aktRepository.save(simpleMapper.mapDtoToEntity(aktDTO));
    }
}