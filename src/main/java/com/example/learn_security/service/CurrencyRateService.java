package com.example.learn_security.service;

import com.example.learn_security.dto.CurrencyDto;
import info.md7.cbar_currency.exceptions.IncorrectContentTypeException;
import info.md7.cbar_currency.exceptions.SpecifiedDateIsAfterException;

import java.util.List;

public interface CurrencyRateService {
    List<CurrencyDto> findAll() throws IncorrectContentTypeException, SpecifiedDateIsAfterException;
}
