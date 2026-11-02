const converter =
  typeof module !== "undefined" && module.exports ? require("./app.js") : window;

QUnit.module("toRoman");

QUnit.test("1 -> I", (assert) => {
  assert.equal(converter.toRoman(1), "I");
});
