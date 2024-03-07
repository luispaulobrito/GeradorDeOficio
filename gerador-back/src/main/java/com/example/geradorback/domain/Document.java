package com.example.geradorback.domain;

import com.example.geradorback.services.enums.DocumentTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "document_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate documentDate;

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

    @Column(name = "document_year", nullable = false)
    private Integer documentYear;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;
    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }
}
