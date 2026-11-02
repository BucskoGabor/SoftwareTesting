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
