package com.example.geradorback.services.dto;

import com.example.geradorback.services.enums.DocumentTypeEnum;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private DocumentTypeEnum documentTypeEnum;
    private LocalDate documentDate;
    private String sender;
    private String senderTitle;
    private String recipient;
    private String recipientTitle;
    private String subject;
    private String text;
    @Hidden
    private Long documentNumber;
    @Hidden
    private Integer documentYear;
    @Hidden
    private String userId;
    @Hidden
    private LocalDate creationDate;
}
