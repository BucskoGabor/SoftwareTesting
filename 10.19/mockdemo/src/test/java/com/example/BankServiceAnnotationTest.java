package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BankServiceAnnotationTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private BankService bankService;

    @Test
    void depositSendsNotification() {
        BankAccount account = new BankAccount();

        bankService.deposit(account, 1000);

        assertEquals(1000.0, account.getBalance());
        verify(notificationService).send("Deposit successful");
    }

    @Test
    void invalidDepositDoesNotSendNotification() {
        BankAccount account = new BankAccount();

        assertThrows(IllegalArgumentException.class,
            () -> bankService.deposit(account, -100));

        verify(notificationService, never()).send(anyString());
    }
}
