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