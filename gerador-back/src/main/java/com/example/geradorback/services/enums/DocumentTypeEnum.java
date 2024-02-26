package com.example.geradorback.services.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum DocumentTypeEnum {
    OL("OFFICIAL LETTER"),
    M("MEMORANDUM");

    private String value;
}
