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
