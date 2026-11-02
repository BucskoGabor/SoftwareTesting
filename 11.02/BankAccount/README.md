# BankAccount – JUnit 5 tesztek

Egy egyszerű bankszámlát modellező Java osztály (`BankAccount`) és a hozzá tartozó JUnit 5 egységtesztek.

## Az osztály

`src/main/java/com/example/BankAccount.java`

| Metódus | Működés |
|---|---|
| `void deposit(double amount)` | Növeli az egyenleget az összeggel |
| `void withdraw(double amount)` | Csökkenti az egyenleget az összeggel |
| `double getBalance()` | Visszaadja az aktuális egyenleget |

- Az új számla egyenlege 0.
- Negatív, 0 vagy nem véges (`NaN`, végtelen) összeg befizetése és felvétele nem engedélyezett.
- Nem lehet több pénzt felvenni, mint az aktuális egyenleg.
- Hibás művelet esetén `IllegalArgumentException` kivételt dob, és az egyenleg nem változik.

## Követelmények

- JDK 21 vagy újabb
- Maven 3.9+

## Tesztek futtatása

```bash
cd 11.02/BankAccount
mvn test
```

A tesztek eredménye a `target/surefire-reports/` mappába kerül.
IntelliJ IDEA-ban a projekt a `pom.xml` megnyitásával importálható, a tesztek a `BankAccountTest` osztályon jobb klikk → *Run* paranccsal futtathatók.

## Tesztesetek

`src/test/java/com/example/BankAccountTest.java`

| # | Teszt | Ellenőrzés |
|---|---|---|
| 1 | `newAccountHasZeroBalance` | Új számla egyenlege 0 |
| 2 | `depositIncreasesBalance` | `deposit(1000)` után az egyenleg 1000 |
| 3 | `multipleDepositsAddUp` | Több befizetés helyesen összeadódik |
| 4 | `withdrawDecreasesBalance` | Befizetés után a `withdraw()` helyesen csökkenti az egyenleget |
| 5 | `wholeBalanceCanBeWithdrawn` | A teljes egyenleg felvehető |
| 6 | `withdrawMoreThanBalanceThrows` | Túl nagy összeg felvétele `IllegalArgumentException`-t dob |
| 7 | `negativeDepositThrows` | Negatív befizetés kivételt dob |
| 8 | `zeroDepositThrows` | 0 összegű befizetés kivételt dob |
| 9 | `negativeWithdrawThrows` | Negatív pénzfelvétel kivételt dob |
| 10 | `zeroWithdrawThrows` | 0 összegű pénzfelvétel kivételt dob |
| 11 | `withdrawFromEmptyAccountThrows` | Üres számláról nem lehet pénzt felvenni |
| 12 | `nonFiniteAmountsThrow` | `NaN` és végtelen összeg befizetése és felvétele kivételt dob |

A kivételt váró tesztek azt is ellenőrzik, hogy a sikertelen művelet után az egyenleg változatlan marad.
