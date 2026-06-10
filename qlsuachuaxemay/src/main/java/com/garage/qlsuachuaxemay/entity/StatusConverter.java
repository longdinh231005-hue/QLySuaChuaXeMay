package com.garage.qlsuachuaxemay.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) {
            return "HoatDong";
        }
        return attribute ? "HoatDong" : "NgungHoatDong";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return true;
        }
        return "HoatDong".equalsIgnoreCase(dbData);
    }
}
