package ru.pnzgu.restauran.controller.director;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.restauran.dto.PostavshikDTO;
import ru.pnzgu.restauran.exception.DocumentExportException;
import ru.pnzgu.restauran.service.DocumentService;
import ru.pnzgu.restauran.service.PostavshikService;

@Controller
@RequestMapping("/director/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final PostavshikService postavshikService;

    @GetMapping("/view/postav")
    public String getPostavDocumentView(Model model) {
        model.addAttribute("postavshikDto", new PostavshikDTO());
        model.addAttribute("postavshikList", postavshikService.getAll());

        return "/director/document/documentPostav";
    }

    @PostMapping(value = "/postav", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> getPostavDocument(@ModelAttribute(name = "postavshikDto") PostavshikDTO postavshikDTO) throws DocumentExportException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"postav.xlsx\"")
                .body(documentService.getPostavDocument(postavshikDTO.getId()));
    }

    @GetMapping(value = "/spis", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> getSpisCurrentDateExelDocument() throws DocumentExportException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"spis.xlsx\"")
                .body(documentService.getSpisCurrentDateExelDocument());
    }


}
