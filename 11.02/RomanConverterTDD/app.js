const MIN_VALUE = 1;
const MAX_VALUE = 3999;

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

function validate(num) {
  if (typeof num !== "number" || !Number.isInteger(num)) {
    throw new TypeError("A bemenetnek egész számnak kell lennie");
  }

  if (num < MIN_VALUE || num > MAX_VALUE) {
    throw new RangeError(`A bemenetnek ${MIN_VALUE} és ${MAX_VALUE} között kell lennie`);
  }
}

function toRoman(num) {
  validate(num);

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
