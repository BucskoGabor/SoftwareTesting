function toRoman(num) {
  return "I".repeat(num);
}

if (typeof module !== "undefined" && module.exports) {
  module.exports = { toRoman };
}

if (typeof window !== "undefined") {
  window.toRoman = toRoman;
}
