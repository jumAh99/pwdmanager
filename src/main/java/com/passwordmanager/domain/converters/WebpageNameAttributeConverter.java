package com.passwordmanager.domain.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.passwordmanager.domain.valueObjects.WebpageName;

@Converter
public class WebpageNameAttributeConverter implements AttributeConverter<WebpageName, String> {
    @Override
    public String convertToDatabaseColumn(WebpageName attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public WebpageName convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new WebpageName(dbData);
    }
}
