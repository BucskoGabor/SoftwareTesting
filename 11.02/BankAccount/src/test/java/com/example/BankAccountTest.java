package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {

    private static final double DELTA = 0.0001;

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
    }

    @Test
    @DisplayName("Új számla egyenlege 0")
    void newAccountHasZeroBalance() {
        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("deposit(1000) után az egyenleg 1000")
    void depositIncreasesBalance() {
        account.deposit(1000);

        assertEquals(1000.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("Több befizetés helyesen összeadódik")
    void multipleDepositsAddUp() {
        account.deposit(1000);
        account.deposit(500);
        account.deposit(250.5);

        assertEquals(1750.5, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("Befizetés után a withdraw() helyesen csökkenti az egyenleget")
    void withdrawDecreasesBalance() {
        account.deposit(1000);

        account.withdraw(300);

        assertEquals(700.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("A teljes egyenleg felvehető")
    void wholeBalanceCanBeWithdrawn() {
        account.deposit(1000);

        account.withdraw(1000);

        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("Túl nagy összeg felvétele IllegalArgumentException kivételt dob, az egyenleg nem változik")
    void withdrawMoreThanBalanceThrows() {
        account.deposit(1000);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(1000.01));
        assertEquals(1000.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("Üres számláról történő pénzfelvétel kivételt dob, az egyenleg nem változik")
    void withdrawFromEmptyAccountThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(1));
        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("Negatív befizetés kivételt dob, az egyenleg nem változik")
    void negativeDepositThrows() {
        account.deposit(500);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
        assertEquals(500.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("0 összegű befizetés kivételt dob, az egyenleg nem változik")
    void zeroDepositThrows() {
        account.deposit(500);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertEquals(500.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("Negatív pénzfelvétel kivételt dob, az egyenleg nem változik")
    void negativeWithdrawThrows() {
        account.deposit(500);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100));
        assertEquals(500.0, account.getBalance(), DELTA);
    }

    @Test
    @DisplayName("0 összegű pénzfelvétel kivételt dob, az egyenleg nem változik")
    void zeroWithdrawThrows() {
        account.deposit(500);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertEquals(500.0, account.getBalance(), DELTA);
    }

    @ParameterizedTest(name = "{0} összegű befizetés és pénzfelvétel kivételt dob")
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void nonFiniteAmountsThrow(double amount) {
        account.deposit(500);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(amount));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(amount));
        assertEquals(500.0, account.getBalance(), DELTA);
    }
}
