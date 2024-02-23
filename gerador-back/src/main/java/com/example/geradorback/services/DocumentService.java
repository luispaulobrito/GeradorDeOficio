package com.example.geradorback.services;

import com.example.geradorback.domain.Document;
import com.example.geradorback.repositories.DocumentRepository;
import com.example.geradorback.services.mapper.DocumentMapper;
import com.example.geradorback.services.records.DocumentDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentMapper documentMapper;
    private final DocumentRepository documentRepository;
    public DocumentDTO saveDocument(DocumentDTO documentDTO){
        Document document = documentRepository.save(documentMapper.toEntity(documentDTO));
        return documentMapper.toDto(document);
    }
}
