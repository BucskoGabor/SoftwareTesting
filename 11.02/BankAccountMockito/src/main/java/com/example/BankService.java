package com.example;

public class BankService {

    public static final String DEPOSIT_SUCCESS_MESSAGE = "Deposit successful";
    private static final String ACCOUNT_CURRENCY = "HUF";

    private final NotificationService notificationService;
    private final ExchangeRateService exchangeRateService;

    public BankService(NotificationService notificationService, ExchangeRateService exchangeRateService) {
        this.notificationService = notificationService;
        this.exchangeRateService = exchangeRateService;
    }

    public void deposit(BankAccount account, double amount) {
        account.deposit(amount);

        notificationService.send(DEPOSIT_SUCCESS_MESSAGE);
    }

    public void depositInCurrency(BankAccount account, double amount, String currency) {
        double rate = exchangeRateService.getRate(currency, ACCOUNT_CURRENCY);

        deposit(account, amount * rate);
    }
}
