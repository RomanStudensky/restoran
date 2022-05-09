package ru.pnzgu.restauran.service;

import org.springframework.transaction.annotation.Transactional;
import ru.pnzgu.restauran.dto.AktDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.AktSpis;
import ru.pnzgu.restauran.store.entity.User;
import ru.pnzgu.restauran.store.repository.AktRepository;
import ru.pnzgu.restauran.store.repository.SostavAktRepository;
import ru.pnzgu.restauran.store.repository.UserRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AktService {

    private final AktRepository aktRepository;
    private final UserRepository userRepository;
    private final SostavAktRepository sostavAktRepository;
    private final SimpleMapper<AktDTO, AktSpis> simpleMapper = new SimpleMapper<>(new AktDTO(), new AktSpis());

    @Transactional(readOnly = true)
    public List<AktDTO> getAll() {
        return aktRepository
                .findAll()
                .stream()
                .map(simpleMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AktDTO get(Long id) {
        return simpleMapper
                .mapEntityToDto(
                        aktRepository
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException(String.format("Акт списания с идентификатором - %s не найден", id))));
    }

    @Transactional
    public AktDTO save(AktDTO dto, String username) {

        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new NotFoundException(String.format("Сотрудник с именем - %s не найден", username)));

        AktSpis aktSpis = simpleMapper.mapDtoToEntity(dto);
        aktSpis.setUser(user);
        aktSpis.setId(null);

        return simpleMapper
                .mapEntityToDto(
                        aktRepository.save(aktSpis)
                );
    }

    @Transactional
    public AktDTO update(Long id, AktDTO dto) {
        User user = aktRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Акт списания с идентификатором - %s не найден", id)))
                .getUser();

        AktSpis aktSpis = simpleMapper.mapDtoToEntity(dto);
        aktSpis.setUser(user);
        aktSpis.setId(id);

        return simpleMapper
                .mapEntityToDto(
                        aktRepository.save(aktSpis)
                );
    }

    @Transactional
    public void delete(Long id) {
        aktRepository.deleteById(id);
    }

    @Transactional
    public void setSumma(Long aktId) {
        AktDTO aktDTO = get(aktId);

        aktDTO.setSumma(sostavAktRepository
                .findAllByAktSpis_Id(aktId)
                .stream()
                .map(sostavAkt -> sostavAkt.getQuantity() * sostavAkt.getPrice())
                .reduce(0.0, Double::sum));

        if (aktDTO.getSumma() == null) {
            aktDTO.setSumma(0.);
        }

        aktRepository.save(simpleMapper.mapDtoToEntity(aktDTO));
    }
}
