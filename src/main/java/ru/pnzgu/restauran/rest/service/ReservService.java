package ru.pnzgu.restauran.rest.service;

import ru.pnzgu.restauran.dto.ReservDTO;
import ru.pnzgu.restauran.dto.StolDTO;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Reserv;
import ru.pnzgu.restauran.store.entity.Stol;
import ru.pnzgu.restauran.store.repository.ReservRepository;
import ru.pnzgu.restauran.store.repository.StolRepository;
import ru.pnzgu.restauran.util.mapping.SimpleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservService {

    private final ReservRepository reservRepository;
    private final StolRepository stolRepository;

    private final SimpleMapper<ReservDTO, Reserv> simpleReservMapper = new SimpleMapper<>(new ReservDTO(), new Reserv());
    private final SimpleMapper<StolDTO, Stol> simpleStolMapper = new SimpleMapper<>(new StolDTO(), new Stol());

    public List<ReservDTO> getAllByStolId(Long id) {
        return reservRepository
                .findAllReservByStolId(id)
                .stream()
                .map(simpleReservMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public ReservDTO get(Long id) {
        return simpleReservMapper.mapEntityToDto(reservRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Бронь с идентификатором - %s не найден", id))));
    }

    public ReservDTO save(Long stolId, ReservDTO dto) {

        Stol stol = stolRepository
                .findById(stolId)
                .orElseThrow(() -> new NotFoundException(String.format("Стол с идентификатором - %s не найден", stolId)));

        dto.setStol(simpleStolMapper.mapEntityToDto(stol));

        return simpleReservMapper
                .mapEntityToDto(
                        reservRepository
                                .save(simpleReservMapper.mapDtoToEntity(dto))
                );
    }

    public ReservDTO update(Long id, ReservDTO dto) {
        reservRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Бронь с идентификатором - %s не найдена", id)));

        Long idStol = reservRepository
                .findStolById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Стол с идентификатором - %s не найдена", id)));

        dto.setId(id);
        StolDTO stol = new StolDTO();
        stol.setId(idStol);

        dto.setStol(stol);
        Reserv newReserv = simpleReservMapper.mapDtoToEntity(dto);

        return simpleReservMapper
                .mapEntityToDto(
                        reservRepository.save(newReserv)
                );
    }

    public void delete(Long id) {
        reservRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Бронь с идентификатором - %s не найден", id)));

        reservRepository.deleteById(id);
    }
}
