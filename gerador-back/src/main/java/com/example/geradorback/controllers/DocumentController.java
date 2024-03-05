package com.example.geradorback.controllers;

import com.example.geradorback.services.DocumentService;
import com.example.geradorback.services.error.NegocioException;
import com.example.geradorback.services.dto.DocumentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Salvar Documento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Documento salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do documento"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor ao salvar o documento")
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DocumentDTO> saveDocument(@RequestBody @Validated DocumentDTO documentDTO) throws NegocioException {
        DocumentDTO newDocumentDTO = documentService.saveDocument(documentDTO);
        return ResponseEntity.ok(newDocumentDTO);
    }
}
