package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExchangeRateServiceTest {

    @Test
    void mockReturnsStubbedRate() {

        ExchangeRateService rateService =
            mock(ExchangeRateService.class);

        when(
            rateService.getRate("EUR", "HUF")
        ).thenReturn(400.0);

        assertEquals(400.0, rateService.getRate("EUR", "HUF"));

        verify(rateService).getRate("EUR", "HUF");
    }

    @Test
    void unstubbedCallReturnsDefault() {

        ExchangeRateService rateService =
            mock(ExchangeRateService.class);

        when(
            rateService.getRate("EUR", "HUF")
        ).thenReturn(400.0);

        assertEquals(0.0, rateService.getRate("USD", "HUF"));
    }
}
