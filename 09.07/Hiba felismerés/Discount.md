 Egy webáruház a rendelési érték alapján kedvezményt biztosít.

    10 000 Ft alatt nincs kedvezmény.
    10 000 Ft-tól 24 999 Ft-ig 5% kedvezmény jár.
    25 000 Ft-tól 49 999 Ft-ig 10% kedvezmény jár.
    50 000 Ft-tól 15% kedvezmény jár.
    VIP ügyfél további 5 százalékpont kedvezményt kap.
    A maximális kedvezmény 20%.
    Negatív rendelési érték nem megengedett.

A függvény a fizetendő összeget adja vissza. 

Hibák:

1. Negatív rendelési érték kezelésének hiánya: A kód nem ellenőrzi a negatív rendelési értéket (orderValue < 0), ami a specifikáció szerint nem megengedett.
2. Határérték hibák a sávoknál:
   - Pontosan 10 000 Ft esetén (orderValue === 10000) nem ad kedvezményt (> 10000 szerepel >= 10000 helyett).
   - Pontosan 50 000 Ft esetén (orderValue === 50000) 10% kedvezményt ad 15% helyett (az else if (orderValue >= 25000 && orderValue <= 50000) feltétel miatt).
3. VIP kedvezmény hibás hozzáadása és a 20%-os plafon torzulása: A discount += 5 utasítás nem 5 százalékpontot (0.05), hanem az 5-ös egész értéket (500%-ot) adja hozzá a kedvezményhez. Emiatt a discount > 0.20 feltétel minden VIP vásárlónál (még egy 100 Ft-os rendelésnél is) azonnal 20%-ra állítja be a kedvezményt.
4. Fizetendő összeg hibás kiszámítása: A return orderValue - discount nem a százalékos kedvezménnyel csökkentett értéket adja vissza (pl. 0.05 Ft-ot von ki az összegből az orderValue * (1 - discount) kiszámítása helyett).
