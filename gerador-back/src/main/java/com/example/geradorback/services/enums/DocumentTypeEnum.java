package com.example.geradorback.services.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum DocumentTypeEnum {
    OF("OFFICIAL LETTER"),
    MEMO("MEMORANDUM");

    private String value;
}
