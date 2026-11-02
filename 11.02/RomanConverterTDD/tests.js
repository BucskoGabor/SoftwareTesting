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
