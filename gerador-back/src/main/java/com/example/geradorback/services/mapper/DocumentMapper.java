package com.example.geradorback.services.mapper;

import com.example.geradorback.domain.Document;
import com.example.geradorback.services.dto.DocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {

    @Override
    @Mapping(source = "user.id", target = "userId")
    DocumentDTO toDto(Document entity);

    @Override
    @Mapping(source = "userId", target = "user.id")
    Document toEntity(DocumentDTO dto);
}
