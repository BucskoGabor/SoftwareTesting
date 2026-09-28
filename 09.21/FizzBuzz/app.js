/*function fizzBuzz(n) {
  if (divideByX(n, 3) && divideByX(n, 5)) {
    return "FizzBuzz";
    } else if (n.toString().includes("3")) {
    return "Fizz";
    } else if (n.toString().includes("5")) {
    return "Buzz";
    } else if (divideByX(n, 3)) {
    return "Fizz";
    } else if (divideByX(n, 5)) {
    return "Buzz";
    } else {
    return n;
  }
}*/

function fizzBuzz(n) {
    var result = "";
    if (divideByX(n, 3) || includesDigit(n, 3)) {
        result += "Fizz";
    }
    if (divideByX(n, 5) || includesDigit(n, 5)) {
        result += "Buzz";
    }
    return result || n;
}

function divideByX(n, x) {
  if (n % x === 0) {
    return true;
    } else {
    return false;
  }
}

function includesDigit(n, digit) {
  if (n.toString().includes(digit.toString())) {
    return true;
    } else {
    return false;
  }
}

if (typeof module !== "undefined" && module.exports) {
  module.exports = { fizzBuzz };
}

if (typeof window !== "undefined") {
  window.fizzBuzz = fizzBuzz;
}
