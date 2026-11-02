const ROMAN_NUMERALS = [
  [1000, "M"],
  [900, "CM"],
  [500, "D"],
  [400, "CD"],
  [100, "C"],
  [90, "XC"],
  [50, "L"],
  [40, "XL"],
  [10, "X"],
  [9, "IX"],
  [5, "V"],
  [4, "IV"],
  [1, "I"]
];

function toRoman(num) {
  let rest = num;
  let result = "";

  for (const [value, symbol] of ROMAN_NUMERALS) {
    while (rest >= value) {
      result += symbol;
      rest -= value;
    }
  }

  return result;
}

if (typeof module !== "undefined" && module.exports) {
  module.exports = { toRoman };
}

if (typeof window !== "undefined") {
  window.toRoman = toRoman;
}
