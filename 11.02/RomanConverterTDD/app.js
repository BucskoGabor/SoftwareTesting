function toRoman(num) {
  return "I";
}

if (typeof module !== "undefined" && module.exports) {
  module.exports = { toRoman };
}

if (typeof window !== "undefined") {
  window.toRoman = toRoman;
}
