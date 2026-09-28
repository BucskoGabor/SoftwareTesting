const { test } = require("qunit");
const { fizzBuzz } = require("./app.js");


test("FizzBuzz function should return 'Fizz' for multiples of 3", (assert) => {
  assert.equal(fizzBuzz(3), "Fizz");
  assert.equal(fizzBuzz(6), "Fizz");
  assert.equal(fizzBuzz(9), "Fizz");
});

test("FizzBuzz function should return 'Buzz' for multiples of 5", (assert) => {
  assert.equal(fizzBuzz(5), "Buzz");
  assert.equal(fizzBuzz(10), "Buzz");
  assert.equal(fizzBuzz(20), "Buzz");
});

test("FizzBuzz function should return 'FizzBuzz' for multiples of both 3 and 5", (assert) => {
  assert.equal(fizzBuzz(15), "FizzBuzz");
  assert.equal(fizzBuzz(30), "FizzBuzz");
});

test("FizzBuzz function should return the number itself for non-multiples of 3 and 5", (assert) => {
  assert.equal(fizzBuzz(1), 1);
  assert.equal(fizzBuzz(2), 2);
  assert.equal(fizzBuzz(4), 4);
  assert.equal(fizzBuzz(7), 7);
});

test("FizzBuzz function should return 'Fizz' for numbers containing the digit 3", (assert) => {
  assert.equal(fizzBuzz(13), "Fizz");
  assert.equal(fizzBuzz(23), "Fizz");
  assert.equal(fizzBuzz(31), "Fizz");
});

test("FizzBuzz function should return 'Buzz' for numbers containing the digit 5", (assert) => {
  assert.equal(fizzBuzz(52), "Buzz");
  assert.equal(fizzBuzz(25), "Buzz");
  assert.equal(fizzBuzz(51), "Buzz");
}); 



