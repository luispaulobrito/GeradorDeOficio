package com.example.geradorback.services.records;

import com.example.geradorback.services.enums.DocumentTypeEnum;

import java.time.LocalDate;

public record DocumentDTO(DocumentTypeEnum documentTypeEnum, LocalDate data, String sender, String senderTitle, String recipient, String recipientTitle, String subject, String text, Long documentNumber) {
}
