 Egy mozijegy alapára 3000 Ft.

Kedvezmények:

    6 éves kor alatt ingyenes;
    6–17 éves korig 30% kedvezmény;
    65 éves kortól 40% kedvezmény;
    diákok 20% kedvezményt kapnak;
    egyszerre csak a legnagyobb kedvezmény alkalmazható;
    3D film esetén 800 Ft felár fizetendő;
    a 3D felárra nem vonatkozik kedvezmény;
    negatív életkor és 120 év feletti életkor érvénytelen.

Hibák:

1. Érvénytelen életkor kezelésének hiánya: A kód nem ellenőrzi a negatív életkort (age < 0) és a 120 év feletti életkort (age > 120), ami a specifikáció szerint érvénytelen.
2. Életkori határértékek hibája:
   - age <= 6: 6 éves korban ingyenesnek veszi, pedig a specifikáció szerint csak 6 éves kor alatt (age < 6) ingyenes.
   - age > 65: 65 éves korban nem adja meg a kedvezményt (65 éves kortól jár 40%, azaz age >= 65).
3. Kedvezmények halmozása: A specifikáció szerint egyszerre csak a legnagyobb kedvezmény alkalmazható. A kód a diák kedvezményt (isStudent) halmozza az életkori kedvezménnyel.
4. 3D felár hibás számítása: A specifikáció szerint 3D film esetén fix 800 Ft felár fizetendő, amire nem vonatkozik kedvezmény. A kód ehelyett az árat szorozza 1,8-cal (price * 1.8), ami nem fix 800 Ft, és a kedvezmény is hatással van rá.
