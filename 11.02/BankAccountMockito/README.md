# BankAccount – Mock objektumok használata (Mockito)

A 3. feladat `BankAccount` osztályának kiegészítése egy értesítési szolgáltatással és egy árfolyam-szolgáltatással.
A külső függőségeket a tesztek Mockito mock objektumokkal helyettesítik.

## Osztályok

`src/main/java/com/example/`

| Fájl | Tartalom |
|---|---|
| `BankAccount.java` | Bankszámla: `deposit()`, `withdraw()`, `getBalance()`; hibás összegnél `IllegalArgumentException` |
| `NotificationService.java` | Értesítési szolgáltatás interfésze: `void send(String message)` |
| `ExchangeRateService.java` | Árfolyam-szolgáltatás interfésze: `double getRate(String from, String to)` |
| `BankService.java` | Befizetést hajt végre, majd értesítést küld |

A `BankService` a két szolgáltatást a konstruktorán keresztül kapja meg (Dependency Injection):

```java
BankService bankService = new BankService(notificationService, exchangeRateService);
```

- `deposit(account, amount)`: befizet a számlára, majd elküldi a `"Deposit successful"` értesítést. Sikertelen befizetésnél a kivétel az értesítés előtt keletkezik, így nem megy ki értesítés.
- `depositInCurrency(account, amount, currency)`: lekéri az árfolyamot (`currency` → `HUF`), az átváltott összeget befizeti, és értesítést küld.

A szolgáltatásoknak nincs valódi implementációja a projektben, a tesztek kizárólag mock objektumokat használnak, így nem küldenek valódi értesítést és nem kérnek le valódi árfolyamot.

## Követelmények

- JDK 21 vagy újabb
- Maven 3.9+

Függőségek (`pom.xml`): JUnit Jupiter 5.12.2, Mockito (`mockito-junit-jupiter`) 5.14.2.

## Tesztek futtatása

```bash
cd 11.02/BankAccountMockito
mvn clean test
```

A tesztek eredménye a `target/surefire-reports/` mappába kerül.
IntelliJ IDEA-ban a projekt a `pom.xml` megnyitásával importálható, a tesztek a `src/test/java` mappán jobb klikk → *Run 'All Tests'* paranccsal futtathatók.

## Tesztek

### `BankServiceTest` (Mockito)

A mockok a `@BeforeEach` metódusban `mock()` hívással készülnek.

| Teszt | Ellenőrzés | Mockito |
|---|---|---|
| `depositIncreasesBalance` | A befizetés megfelelően növeli a számla egyenlegét | `mock()` |
| `successfulDepositCallsSend` | Sikeres befizetés után meghívódik a `send()` metódus | `verify()` |
| `successfulDepositSendsCorrectNotificationOnce` | Pontosan egyszer a `"Deposit successful"` értesítés megy ki, más hívás nincs | `verify(…, times(1))`, `verifyNoMoreInteractions()` |
| `negativeDepositSendsNoNotification` | Negatív befizetésnél kivétel, nincs értesítés | `verify(…, never())` |
| `zeroDepositSendsNoNotification` | 0 összegű befizetésnél kivétel, nincs értesítés | `verifyNoInteractions()` |
| `stubbedExchangeRateIsReturned` | A mock a beállított 400.0 árfolyamot adja vissza | `when().thenReturn()`, `verify()` |
| `depositInCurrencyUsesStubbedRate` | 10 EUR befizetése 400-as árfolyamon 4000 Ft egyenleget ad, és értesítést küld | `when().thenReturn()`, `verify()` |
| `failedDepositInCurrencySendsNoNotification` | Sikertelen devizás befizetésnél nincs értesítés | `when().thenReturn()`, `verifyNoInteractions()` |

### `BankAccountTest` (JUnit 5)

A `BankAccount` osztály tesztjei a 3. feladatból: kezdő egyenleg, befizetés, pénzfelvétel, a hibás összegek kivételei, és hogy sikertelen művelet után az egyenleg változatlan marad.
