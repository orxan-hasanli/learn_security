package com.example.learn_security.dto;

import com.example.learn_security.model.CurrencyChange;

public record CurrencyDto(
        String code,
        String name,
        Double value,
        CurrencyChange currencyChange
) {
}
