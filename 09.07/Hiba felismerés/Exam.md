 Egy vizsga két részből áll:

    elmélet: maximum 60 pont;
    gyakorlat: maximum 40 pont.

A vizsga csak akkor sikeres, ha:

    elméletből legalább 30 pontot,
    gyakorlatból legalább 20 pontot elér a hallgató.

Ha mindkét minimum teljesül, az összpontszám alapján:

Pont Jegy 0–49 1 50–59 2 60–69 3 70–84 4 85–100 5

Érvénytelen pontszám esetén hibát kell jelezni. 

Hibák:

1. Érvénytelen pontszámok kezelésének hiánya: Nem ellenőrzi a maximális pontszámokat (elmélet max 60, gyakorlat max 40) és a negatív pontszámokat, nem jelez hibát érvénytelen adat esetén.
2. Sikertelenségi feltétel logikai hibája: A theory < 30 && practice < 20 feltétel miatt csak akkor ad 1-est a minimum hiánya miatt, ha mindkét részvizsga bukás. Helyesen VAGY (||) kapcsolat kellene (theory < 30 || practice < 20), mert bármelyik minimum hiánya esetén megbukott a hallgató.
3. Osztályzási ponthatárok hibái:
   - Pontosan 60 pontnál a kód 2-est ad (total <= 60), pedig 60-tól már 3-as jár.
   - Pontosan 70 pontnál a kód 3-ast ad (total <= 70), pedig 70-től már 4-es jár.
   - Pontosan 85 pontnál a kód 4-est ad (total <= 85), pedig 85-től már 5-ös jár.

Javítás:

- Érvénytelen pontszám (elmélet 0–60, gyakorlat 0–40 tartományon kívül, vagy nem szám) esetén hibát dob.
- Ha bármelyik minimum nem teljesül (theory < 30 || practice < 20), a jegy 1.
- A ponthatárok alsó határa is az adott jegyhez tartozik: < 50 → 1, < 60 → 2, < 70 → 3, < 85 → 4, egyébként 5.

```js
function calculateGrade(theory, practice) {
    if (!Number.isFinite(theory) || !Number.isFinite(practice) ||
        theory < 0 || theory > 60 ||
        practice < 0 || practice > 40) {
        throw new Error("Érvénytelen pontszám");
    }

    if (theory < 30 || practice < 20) {
        return 1;
    }

    const total = theory + practice;

    if (total < 50) {
        return 1;
    } else if (total < 60) {
        return 2;
    } else if (total < 70) {
        return 3;
    } else if (total < 85) {
        return 4;
    } else {
        return 5;
    }
}
```