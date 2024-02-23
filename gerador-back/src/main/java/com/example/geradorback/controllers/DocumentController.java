package com.example.geradorback.controllers;

import com.example.geradorback.services.DocumentService;
import com.example.geradorback.services.error.NegocioException;
import com.example.geradorback.services.records.DocumentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DocumentDTO> saveDocument(@RequestBody @Validated DocumentDTO documentDTO) throws NegocioException {
        DocumentDTO newDocumentDTO = documentService.saveDocument(documentDTO);
        return ResponseEntity.ok(newDocumentDTO);
    }
}
