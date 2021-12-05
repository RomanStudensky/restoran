package ru.pnzgu.restauran.service;

import ru.pnzgu.restauran.dto.NakladDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.*;
import ru.pnzgu.restauran.store.repository.DogovorRepository;
import ru.pnzgu.restauran.store.repository.NakladRepository;
import ru.pnzgu.restauran.store.repository.PostavshikRepository;
import ru.pnzgu.restauran.store.repository.SostavPostavRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NakladService {

    private final NakladRepository nakladRepository;
    private final SostavPostavRepository sostavPostavRepository;
    private final PostavshikRepository postavshikRepository;
    private final DogovorRepository dogovorRepository;
    private final SimpleMapper<NakladDTO, Naklad> simpleMapper = new SimpleMapper<>(new NakladDTO(), new Naklad());

    public List<NakladDTO> getAll() {
        return nakladRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public NakladDTO get(Long id) {

        return simpleMapper
                .mapEntityToDto(
                        nakladRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", id))));
    }

    public NakladDTO save(NakladDTO nakladDTO, Long postavshikId, Long dogovorId) {

        Naklad naklad = simpleMapper.mapDtoToEntity(nakladDTO);

        Postavshik postavshik = postavshikRepository
                .findById(postavshikId)
                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", postavshikId)));

        Dogovor dogovor = dogovorRepository
                .findById(dogovorId)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", dogovorId)));

        naklad.setId(null);
        naklad.setSumma(
                sostavPostavRepository
                        .findAllByTovarNakladId(naklad.getId())
                        .stream()
                        .map(SostavPostav::getSumma)
                        .reduce(0.0D, Double::sum));

        naklad.setDogovor(dogovor);
        naklad.setPostavshik(postavshik);

        return simpleMapper
                .mapEntityToDto(
                        nakladRepository
                                .save(naklad)
                );
    }



    public NakladDTO update(Long id, NakladDTO nakladDTO, Long postavshikId, Long dogovorId) {

        Naklad naklad = simpleMapper.mapDtoToEntity(nakladDTO);

        nakladRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Накладная с идентификатором - %s не найдена", id)));

        Postavshik postavshik = postavshikRepository
                .findById(postavshikId)
                .orElseThrow(() -> new NotFoundException(String.format("Поставщик с идентификатором - %s не найден", postavshikId)));

        Dogovor dogovor = dogovorRepository
                .findById(dogovorId)
                .orElseThrow(() -> new NotFoundException(String.format("Договор с идентификатором - %s не найден", dogovorId)));


        naklad.setSumma(
                sostavPostavRepository
                        .findAllByTovarNakladId(naklad.getId())
                        .stream()
                        .map(SostavPostav::getSumma)
                        .reduce(0.0D, Double::sum));

        naklad.setDogovor(dogovor);
        naklad.setPostavshik(postavshik);

        return simpleMapper
                .mapEntityToDto(
                        nakladRepository
                                .save(naklad)
                );
    }

    public void updateSumma(Long id, Double summa) {
        Naklad naklad = nakladRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Накладная с идентификатором - %s не найдена", id)));

        naklad.setSumma(summa);

        nakladRepository.save(naklad);
    }

    public void delete(Long id) {
        nakladRepository.deleteById(id);
    }

    public Long getFirstNaklad() {
        return nakladRepository.findMinIdNaklad().orElse(0L);
    }
}
