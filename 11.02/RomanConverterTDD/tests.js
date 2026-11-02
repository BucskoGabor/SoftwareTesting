const converter =
  typeof module !== "undefined" && module.exports ? require("./app.js") : window;

QUnit.module("toRoman");

QUnit.test("1 -> I", (assert) => {
  assert.equal(converter.toRoman(1), "I");
});

QUnit.test("az I ismétlése: 2 -> II, 3 -> III", (assert) => {
  assert.equal(converter.toRoman(2), "II");
  assert.equal(converter.toRoman(3), "III");
});

QUnit.test("V és utána I-ek: 5, 6, 7, 8", (assert) => {
  assert.equal(converter.toRoman(5), "V");
  assert.equal(converter.toRoman(6), "VI");
  assert.equal(converter.toRoman(7), "VII");
  assert.equal(converter.toRoman(8), "VIII");
});

QUnit.test("kivonásos alak: 4 -> IV, 9 -> IX", (assert) => {
  assert.equal(converter.toRoman(4), "IV");
  assert.equal(converter.toRoman(9), "IX");
});

QUnit.test("X: 10, 14, 19, 20, 39", (assert) => {
  assert.equal(converter.toRoman(10), "X");
  assert.equal(converter.toRoman(14), "XIV");
  assert.equal(converter.toRoman(19), "XIX");
  assert.equal(converter.toRoman(20), "XX");
  assert.equal(converter.toRoman(39), "XXXIX");
});

QUnit.test("L, XL, XC: 40, 50, 58, 90, 99", (assert) => {
  assert.equal(converter.toRoman(40), "XL");
  assert.equal(converter.toRoman(50), "L");
  assert.equal(converter.toRoman(58), "LVIII");
  assert.equal(converter.toRoman(90), "XC");
  assert.equal(converter.toRoman(99), "XCIX");
});

QUnit.test("C, CD, D, CM, M: 100, 400, 500, 900, 1000", (assert) => {
  assert.equal(converter.toRoman(100), "C");
  assert.equal(converter.toRoman(400), "CD");
  assert.equal(converter.toRoman(500), "D");
  assert.equal(converter.toRoman(900), "CM");
  assert.equal(converter.toRoman(1000), "M");
});

QUnit.test("a feladat példái és a felső határ", (assert) => {
  assert.equal(converter.toRoman(1), "I");
  assert.equal(converter.toRoman(4), "IV");
  assert.equal(converter.toRoman(9), "IX");
  assert.equal(converter.toRoman(14), "XIV");
  assert.equal(converter.toRoman(58), "LVIII");
  assert.equal(converter.toRoman(1994), "MCMXCIV");
  assert.equal(converter.toRoman(3999), "MMMCMXCIX");
});

QUnit.test("0 és negatív szám érvénytelen", (assert) => {
  assert.throws(() => converter.toRoman(0), RangeError);
  assert.throws(() => converter.toRoman(-1), RangeError);
  assert.throws(() => converter.toRoman(-3999), RangeError);
});

QUnit.test("3999 feletti szám érvénytelen", (assert) => {
  assert.throws(() => converter.toRoman(4000), RangeError);
  assert.throws(() => converter.toRoman(10000), RangeError);
});

QUnit.test("nem egész szám vagy nem szám érvénytelen", (assert) => {
  assert.throws(() => converter.toRoman(4.5), TypeError);
  assert.throws(() => converter.toRoman(0.1), TypeError);
  assert.throws(() => converter.toRoman(NaN), TypeError);
  assert.throws(() => converter.toRoman(Infinity), TypeError);
  assert.throws(() => converter.toRoman("5"), TypeError);
  assert.throws(() => converter.toRoman(null), TypeError);
  assert.throws(() => converter.toRoman(undefined), TypeError);
});
