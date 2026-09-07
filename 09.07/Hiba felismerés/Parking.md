 Egy parkoló díjszabása:

    az első 15 perc ingyenes;
    15 perc felett minden megkezdett óra 600 Ft;
    a napi maximális parkolási díj 5000 Ft;
    hétvégén a normál díj 50%-át kell fizetni;
    VIP ügyfél további 20% kedvezményt kap;
    negatív parkolási idő nem megengedett.

A függvény a fizetendő parkolási díjat adja vissza. 

Hibák:

1. VIP kedvezmény hibás alkalmazása és negatív díj keletkezése: A fee = fee - 20 nem 20%-os kedvezményt von le (fee * 0.8), hanem 20 Ft-ot von ki a díjból. Emiatt pl. 15 perces parkolásnál a kód 0 Ft alapdíjból kivon 20 Ft-ot, és negatív (-20 Ft) fizetendő díjat ad vissza a VIP ügyfélnek.
2. "Megkezdett óra" kerekítési hibája: A Math.floor(minutes / 60) kerekítés miatt pl. 20 perc parkolásnál 0 órát számol (0 Ft), pedig a specifikáció szerint minden megkezdett óra fizetős (600 Ft). Helyesen Math.ceil vagy a 15 perc feletti megkezdett órák kerekítése szükséges.
3. Ingyenes időszak határa: minutes < 15 esetén pontosan 15 percre nem az ingyenes ággal tér vissza, hanem a fő ágra fut tovább.
4. Negatív parkolási idő kezelésének hiánya: A kód nem ellenőrzi a negatív parkolási időt (minutes < 0).
5. Specifikációs kétértelműség (kedvezmények halmozása): Nem tisztázott, hogy hétvégén a VIP ügyfél a csökkentett hétvégi díjból kap további 20%-ot (multiplikatív: 600 * 0.5 * 0.8 = 240 Ft, össz-kedvezmény: 60%), vagy a kedvezményráta adódik össze (additív: 50% + 20% = 70%, azaz 600 * 0.3 = 180 Ft).
6. Napi maximális díj (5000 Ft) korlátjának rossz elhelyezése: A kód a 5000 Ft-os plafont a kedvezmények levonása után alkalmazza legvégül (if fee > 5000 fee = 5000). Emiatt egy egész napos (pl. 24 órás) parkolásnál a kedvezmények hatása teljesen elveszik, és a VIP ügyfél is ugyanúgy 5000 Ft-ot fizet, mint a normál ügyfél. Helyesen az alapdíjra kellene alkalmazni a 5000 Ft-os maximumot a kedvezmények kiszámítása előtt.