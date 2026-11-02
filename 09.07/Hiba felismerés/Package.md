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

Javítás:

- Ha a tömeg vagy bármelyik méret 0, negatív vagy nem szám, a visszatérési érték "INVALID".
- A határok megengedők (<=): SMALL ≤ 2 kg és ≤ 30 cm, MEDIUM ≤ 10 kg és ≤ 60 cm, LARGE ≤ 30 kg és ≤ 120 cm.
- Mindhárom méretnek teljesítenie kell a korlátot, ezért a legnagyobb méretet (Math.max) hasonlítja össze a határral.

```js
function classifyPackage(weight, length, width, height) {
    if (!Number.isFinite(weight) || !Number.isFinite(length) ||
        !Number.isFinite(width) || !Number.isFinite(height) ||
        weight <= 0 || length <= 0 || width <= 0 || height <= 0) {
        return "INVALID";
    }

    const maxSize = Math.max(length, width, height);

    if (weight <= 2 && maxSize <= 30) {
        return "SMALL";
    }

    if (weight <= 10 && maxSize <= 60) {
        return "MEDIUM";
    }

    if (weight <= 30 && maxSize <= 120) {
        return "LARGE";
    }

    return "OVERSIZE";
}
```