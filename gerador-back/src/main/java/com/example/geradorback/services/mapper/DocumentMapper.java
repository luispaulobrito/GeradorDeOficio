package com.example.geradorback.services.mapper;

import com.example.geradorback.domain.Document;
import com.example.geradorback.services.records.DocumentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {
}
