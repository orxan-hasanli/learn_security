package com.example.learn_security.controller;

import com.example.learn_security.dto.CurrencyDto;
import com.example.learn_security.service.CurrencyRateService;
import info.md7.cbar_currency.exceptions.IncorrectContentTypeException;
import info.md7.cbar_currency.exceptions.SpecifiedDateIsAfterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("public/currency")
@RequiredArgsConstructor
public class CurrencyRateController {
    private final CurrencyRateService currencyRateService;

    @GetMapping
    public ResponseEntity<List<CurrencyDto>> findAll() throws SpecifiedDateIsAfterException, IncorrectContentTypeException {
        return ResponseEntity.ok(currencyRateService.findAll());
    }
}
