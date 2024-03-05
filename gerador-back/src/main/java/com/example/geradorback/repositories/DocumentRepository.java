package com.example.geradorback.repositories;

import com.example.geradorback.domain.Document;
import com.example.geradorback.services.enums.DocumentTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
    Document findFirstByDocumentYearAndDocumentTypeEnumOrderByDocumentNumberDesc(Integer year, DocumentTypeEnum type);
}
