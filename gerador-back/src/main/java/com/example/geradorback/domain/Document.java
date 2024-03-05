package com.example.geradorback.domain;

import com.example.geradorback.services.enums.DocumentTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "document")
@Entity(name = "document")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private DocumentTypeEnum documentTypeEnum;

    @Column(name = "data", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @Column(name = "sender", nullable = false, length = 100)
    private String sender;

    @Column(name = "sender_title", nullable = false, length = 100)
    private String senderTitle;

    @Column(name = "recipient", nullable = false, length = 100)
    private String recipient;

    @Column(name = "recipient_title", nullable = false, length = 100)
    private String recipientTitle;

    @Column(name = "subject", nullable = false, length = 100)
    private String subject;

    @Column(name = "text", nullable = false, length = 1000)
    private String text;

    @Column(name = "document_number", nullable = false)
    private Long documentNumber;
}