package com.example.learn_security.service.impl;

import com.example.learn_security.dto.CurrencyDto;
import com.example.learn_security.model.CurrencyChange;
import com.example.learn_security.service.CurrencyRateService;
import com.google.common.collect.Streams;
import info.md7.cbar_currency.exceptions.IncorrectContentTypeException;
import info.md7.cbar_currency.exceptions.SpecifiedDateIsAfterException;
import info.md7.cbar_currency.model.Currency;
import info.md7.cbar_currency.model.CurrencyCode;
import info.md7.cbar_currency.util.CurrencyRate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.example.learn_security.model.CurrencyChange.*;
import static info.md7.cbar_currency.model.CurrencyCode.*;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private static final Set<CurrencyCode> targetCurrencyValues = Set.of(AED, AUD, BYN, CAD, EUR, GBP, GEL, RUB, TRY,USD, UAH, XAG, XAU, XPD, XPT);

    @Override
    public List<CurrencyDto> findAll() throws IncorrectContentTypeException, SpecifiedDateIsAfterException {
        List<Currency> actualCurrencyRates = CurrencyRate.getActualCurrencyRates();
        List<Currency> yesterdayCurrencyRate = CurrencyRate.getCurrencyRatesForDate(LocalDate.now().minusDays(1));

        return Streams.zip(actualCurrencyRates.stream(), yesterdayCurrencyRate.stream(), (currencyFrom, currencyTo) -> {
            CurrencyChange currencyChange;
            if (currencyFrom.getValue().doubleValue() > currencyTo.getValue().doubleValue()) {
                currencyChange = INCREASE;
            } else if (currencyFrom.getValue().doubleValue() == currencyTo.getValue().doubleValue()) {
                currencyChange = STABLE;
            } else {
                currencyChange = DECREASE;
            }
            return new CurrencyDto(
                    String.valueOf(currencyFrom.getCode()),
                    currencyFrom.getName(),
                    currencyFrom.getValue().doubleValue(),
                    currencyChange
            );
        }).filter(currencyDto -> targetCurrencyValues.contains(CurrencyCode.valueOf(currencyDto.code()))).toList();
    }
}
