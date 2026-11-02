# Arab szám → római szám konverter (TDD)

A `toRoman(num)` függvény az 1 és 3999 közötti egész számokat római számmá alakítja.
A megoldás Test Driven Development (Red → Green → Refactor) módszerrel, QUnit tesztekkel készült.

## Fájlok

| Fájl | Tartalom |
|---|---|
| `app.js` | A `toRoman` függvény |
| `tests.js` | QUnit tesztek |
| `test.html` | A tesztek futtatása böngészőben |
| `package.json` | npm szkriptek és a QUnit függőség |

## Működés

| Bemenet | Eredmény |
|---|---|
| 1 | I |
| 4 | IV |
| 9 | IX |
| 14 | XIV |
| 58 | LVIII |
| 1994 | MCMXCIV |
| 3999 | MMMCMXCIX |

Érvénytelen bemenet esetén a függvény kivételt dob:

- `RangeError`: 1-nél kisebb (0, negatív) vagy 3999-nél nagyobb egész szám;
- `TypeError`: nem egész szám (pl. `4.5`, `NaN`, `Infinity`) vagy nem szám típusú érték (pl. `"5"`, `null`, `undefined`).

```js
const { toRoman } = require("./app.js");

toRoman(1994);
toRoman(0);
```

Az első hívás eredménye `"MCMXCIV"`, a második `RangeError`-t dob.

## Telepítés

Szükséges: Node.js és npm.

```bash
cd 11.02/RomanConverterTDD
npm install
```

## Tesztek futtatása

Parancssorból:

```bash
npm test
```

Böngészőben: az `npm install` után nyisd meg a `test.html` fájlt.

## TDD folyamat

Minden lépés külön commit, a commit üzenet elején a fázis neve áll:

- `RED:` új, még bukó teszt;
- `GREEN:` a teszt teljesítéséhez szükséges minimális kód;
- `REFACTOR:` a kód átszervezése, a tesztek zöldek maradnak.

A folyamat megtekintése:

```bash
git log --reverse --oneline -- 11.02/RomanConverterTDD
```
