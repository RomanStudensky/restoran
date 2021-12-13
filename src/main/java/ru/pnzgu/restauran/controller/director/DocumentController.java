package ru.pnzgu.restauran.controller.director;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.PostavshikDTO;
import ru.pnzgu.restauran.dto.SotrudnikDTO;
import ru.pnzgu.restauran.exception.DocumentExportException;
import ru.pnzgu.restauran.service.DocumentService;
import ru.pnzgu.restauran.service.PostavshikService;
import ru.pnzgu.restauran.service.SotrudnikService;
import ru.pnzgu.restauran.util.excel.ProdDto;
import ru.pnzgu.restauran.util.excel.SpisDto;
import ru.pnzgu.restauran.util.mapping.DateOptions;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/director/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final PostavshikService postavshikService;
    private final SotrudnikService sotrudnikService;

    @GetMapping("/view/postav")
    public String getPostavDocumentView(Model model) {
        model.addAttribute("postavshikDto", new PostavshikDTO());
        model.addAttribute("postavshikList", postavshikService.getAll());

        return "/director/document/documentPostav";
    }

    @GetMapping("/view/spis")
    public String getSpisBetweenDateExelDocumentView(Model model) {
        model.addAttribute("spisDto", new SpisDto());

        return "/director/document/documentSpis";
    }

    @GetMapping("/view/prodaza")
    public String getProdazaDocumentView(Model model) {
        model.addAttribute("prod", new ProdDto());

        return "/director/document/documentProd";
    }

    @PostMapping(value = "/postav", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> getPostavDocument(@ModelAttribute(name = "postavshikDto") PostavshikDTO postavshikDTO) throws DocumentExportException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"postav.xlsx\"")
                .body(documentService.getPostavDocument(postavshikDTO.getId()));
    }

    @PostMapping(value = "/spis", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> getSpisBetweenDateExelDocument(@ModelAttribute(name = "spisDto") SpisDto spisDto) throws DocumentExportException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"spis.xlsx\"")
                .body(documentService.getSpisCurrentDateExelDocument(spisDto.getBeginDate(), spisDto.getEndDate()));
    }

    @PostMapping(value = "/prodaza", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> getProdazaExelDocument(@ModelAttribute(name = "prodDto") ProdDto prodDto) throws DocumentExportException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"prodaza.xlsx\"")
                .body(documentService.getProdazaExelDocument(prodDto.getDate()));
    }



}
