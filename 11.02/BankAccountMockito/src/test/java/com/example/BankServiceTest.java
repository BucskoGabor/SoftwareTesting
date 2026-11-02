package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class BankServiceTest {

    private static final double DELTA = 0.0001;

    private NotificationService notificationService;
    private ExchangeRateService exchangeRateService;
    private BankService bankService;
    private BankAccount account;

    @BeforeEach
    void setUp() {
        notificationService = mock(NotificationService.class);
        exchangeRateService = mock(ExchangeRateService.class);
        bankService = new BankService(notificationService, exchangeRateService);
        account = new BankAccount();
    }

    @Test
    @DisplayName("A befizetés megfelelően növeli a számla egyenlegét")
    void depositIncreasesBalance() {
        bankService.deposit(account, 1000);

        assertEquals(1000.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("Sikeres befizetés után meghívódik a NotificationService.send() metódusa")
    void successfulDepositCallsSend() {
        bankService.deposit(account, 1000);

        verify(notificationService).send(anyString());
    }

    @Test
    @DisplayName("Sikeres befizetés után pontosan egyszer a megfelelő értesítés megy ki")
    void successfulDepositSendsCorrectNotificationOnce() {
        bankService.deposit(account, 1000);

        verify(notificationService, times(1)).send(BankService.DEPOSIT_SUCCESS_MESSAGE);
        verifyNoMoreInteractions(notificationService);
    }

    @Test
    @DisplayName("Negatív összegű, sikertelen befizetés esetén nem történik értesítés")
    void negativeDepositSendsNoNotification() {
        assertThrows(IllegalArgumentException.class, () -> bankService.deposit(account, -100));

        verify(notificationService, never()).send(anyString());
        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("0 összegű, sikertelen befizetés esetén nem történik értesítés")
    void zeroDepositSendsNoNotification() {
        assertThrows(IllegalArgumentException.class, () -> bankService.deposit(account, 0));

        verifyNoInteractions(notificationService);
        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("A when().thenReturn() által beállított árfolyamot adja vissza a mock")
    void stubbedExchangeRateIsReturned() {
        when(exchangeRateService.getRate("EUR", "HUF")).thenReturn(400.0);

        assertEquals(400.0, exchangeRateService.getRate("EUR", "HUF"), DELTA);
        verify(exchangeRateService).getRate("EUR", "HUF");
    }

    @Test
    @DisplayName("Devizás befizetés a beállított árfolyamon számol, és értesítést küld")
    void depositInCurrencyUsesStubbedRate() {
        when(exchangeRateService.getRate("EUR", "HUF")).thenReturn(400.0);

        bankService.depositInCurrency(account, 10, "EUR");

        assertEquals(4000.0, account.getBalance(), DELTA);
        verify(exchangeRateService).getRate("EUR", "HUF");
        verify(notificationService).send(BankService.DEPOSIT_SUCCESS_MESSAGE);
    }

    @Test
    @DisplayName("Sikertelen devizás befizetés esetén nem történik értesítés")
    void failedDepositInCurrencySendsNoNotification() {
        when(exchangeRateService.getRate("EUR", "HUF")).thenReturn(400.0);

        assertThrows(IllegalArgumentException.class, () -> bankService.depositInCurrency(account, -10, "EUR"));

        verifyNoInteractions(notificationService);
        assertEquals(0.0, account.getBalance(), DELTA);
    }
}
