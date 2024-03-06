package com.example.geradorback.services;

import com.example.geradorback.domain.User;
import org.springframework.security.core.Authentication;
import com.example.geradorback.domain.Document;
import com.example.geradorback.repositories.DocumentRepository;
import com.example.geradorback.services.enums.DocumentTypeEnum;
import com.example.geradorback.services.mapper.DocumentMapper;
import com.example.geradorback.services.dto.DocumentDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentMapper documentMapper;
    private final DocumentRepository documentRepository;
    private static final Logger logger = LogManager.getLogger(DocumentService.class);
    public DocumentDTO saveDocument(DocumentDTO documentDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        documentDTO.setUserId(((User) authentication.getPrincipal()).getId());
        documentDTO.setDocumentYear(extractYearFromDate(documentDTO.getDocumentDate()));
        documentDTO.setDocumentNumber(generateDocumentNumber(documentDTO));
        Document document = documentRepository.save(documentMapper.toEntity(documentDTO));
        logger.info("Documento salvo no banco de dados");
        return documentMapper.toDto(document);
    }


    private Integer extractYearFromDate(LocalDate date) {
        return date.getYear();
    }
    private Long generateDocumentNumber(DocumentDTO documentDTO) {
        int currentDocumentYear = documentDTO.getDocumentYear();
        DocumentTypeEnum type = documentDTO.getDocumentTypeEnum();
        Document lastDocument = documentRepository.findFirstByDocumentYearAndDocumentTypeEnumOrderByDocumentNumberDesc(currentDocumentYear, type);

        Long nextDocumentNumber = 1L;
        if (Objects.nonNull(lastDocument)) {
            nextDocumentNumber = lastDocument.getDocumentNumber() + 1;
        }

        return nextDocumentNumber;
    }
}
