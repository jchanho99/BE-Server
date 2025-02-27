package com.example.writeagain_be.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BlogTypeConverter implements AttributeConverter<User.BlogType, String> {
    @Override
    public String convertToDatabaseColumn(User.BlogType blogType) {
        return blogType != null ? blogType.getValue() : null;
    }

    @Override
    public User.BlogType convertToEntityAttribute(String value) {
        return User.BlogType.fromValue(value);
    }
}

