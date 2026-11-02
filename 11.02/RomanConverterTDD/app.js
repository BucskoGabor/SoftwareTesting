function toRoman(num) {
  if (num === 9) {
    return "IX";
  }

  if (num === 4) {
    return "IV";
  }

  let result = "";

  if (num >= 5) {
    result += "V";
    num -= 5;
  }

  return result + "I".repeat(num);
}

if (typeof module !== "undefined" && module.exports) {
  module.exports = { toRoman };
}

if (typeof window !== "undefined") {
  window.toRoman = toRoman;
}
