package ru.pnzgu.restauran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pnzgu.restauran.dto.AktDTO;
import ru.pnzgu.restauran.dto.NakladDTO;
import ru.pnzgu.restauran.dto.PostavshikDTO;
import ru.pnzgu.restauran.exception.DocumentExportException;
import ru.pnzgu.restauran.exception.NotFoundException;
import ru.pnzgu.restauran.store.entity.Prodaza;
import ru.pnzgu.restauran.store.entity.SostavProd;
import ru.pnzgu.restauran.store.repository.*;
import ru.pnzgu.restauran.util.excel.ExcelExportUtil;
import ru.pnzgu.restauran.util.mapping.Mappers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final NakladRepository nakladRepository;
    private final SostavPostavRepository sostavPostavRepository;
    private final PostavshikService postavshikService;
    private final ProdazaRepository prodazaRepository;

    private final AktRepository aktRepository;
    private final SostavAktRepository sostavAktRepository;

    public byte[] getPostavDocument(Long postavshikId) throws DocumentExportException {
        List<NakladDTO> nakladList = nakladRepository
                .findAllByPostavshikId(postavshikId)
                .stream()
                .map(Mappers.NAKLAD::mapEntityToDto)
                .collect(Collectors.toList());

        nakladList.forEach(naklad -> naklad.setSostav(
                sostavPostavRepository
                .findAllByTovarNakladId(naklad.getId())
                .stream()
                .map(Mappers.SOSTAV_POSTAV::mapEntityToDto)
                .collect(Collectors.toList())));

        PostavshikDTO postavshikDTO = postavshikService.get(postavshikId);

        try {
            return ExcelExportUtil.createPostavExelDocument(postavshikDTO, nakladList).toByteArray();
        } catch (IOException e) {
            throw new DocumentExportException("Не удалось сформировать отчёт по поставщику, повторите попытку");
        }

    }

    public byte[] getSpisCurrentDateExelDocument(LocalDate date1, LocalDate date2) throws DocumentExportException {


        List<AktDTO> aktDTOS = aktRepository
                .findByDateAktBetween(date1, date2)
                .stream()
                .map(Mappers.AKT::mapEntityToDto)
                .collect(Collectors.toList());

        try {
            return ExcelExportUtil.createSpisCurrentDateExelDocument(aktDTOS).toByteArray();
        } catch (IOException e) {
            throw new DocumentExportException("Не удалось сформировать отчёт по списанным продуктам, повторите попытку");
        }

    }

    public byte[] getProdazaExelDocument(LocalDate date) throws DocumentExportException {

        List<Prodaza> prodazas = new ArrayList<>(prodazaRepository.findAllByDateProd(date));

        List<SostavProd> prodazaDTOS = prodazas
                .stream()
                .map(Prodaza::getSostavProd)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        try {
            return ExcelExportUtil.createProdazaExelDocument(
                    prodazas
                            .stream()
                            .findAny().orElseThrow(() -> new NotFoundException("Продажи не найдены")), date, prodazaDTOS).toByteArray();
        } catch (IOException e) {
            throw new DocumentExportException("Не удалось сформировать отчёт по списанным продуктам, повторите попытку");
        }

    }
}
