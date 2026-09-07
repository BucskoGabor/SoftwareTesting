 Egy futárszolgálat a csomagokat méret és tömeg alapján kategorizálja.

    Small legfeljebb 2 kg; mindhárom mérete legfeljebb 30 cm.
    Medium legfeljebb 10 kg; egyik mérete sem nagyobb 60 cm-nél.
    Large legfeljebb 30 kg; egyik mérete sem nagyobb 120 cm-nél.

    Egyéb esetben: OVERSIZE

    Unordered List ItemÉrvénytelen a csomag, ha:

bármely mérete 0 vagy negatív; tömege 0 vagy negatív.

Ebben az esetben:

INVALID 

Hibák:

1. Érvénytelen adatok kezelésének hiánya: Nem ellenőrzi a 0 vagy negatív tömeget és méreteket (<= 0), nem ad vissza "INVALID" értéket.
2. SMALL kategória határértékeinek hibája: Szigorú kisebb (<) operátort használ <= helyett (weight < 2, length < 30, stb.), így a pontosan 2 kg-os tömegű vagy 30 cm-es méretű csomag nem lesz SMALL.
3. MEDIUM kategória logikai hibája: A méreteknél VAGY (||) kapcsolatot használ ÉS (&&) helyett (length <= 60 || width <= 60 || height <= 60). Így ha bármelyik méret 60 cm alatt van (pl. egy 200x200x10 cm-es csomag), azt is MEDIUM-nak sorolja be, pedig a specifikáció szerint egyik mérete sem lehet nagyobb 60 cm-nél.