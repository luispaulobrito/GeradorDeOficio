package com.example.geradorback.services.mapper;

import com.example.geradorback.domain.Document;
import com.example.geradorback.services.dto.DocumentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {
}
