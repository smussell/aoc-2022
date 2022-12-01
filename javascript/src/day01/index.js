import run from "aocrunner";

const parseInput = (rawInput) => {
  return rawInput
    .split('\n\n')
    .map(d => d.split('\n').map(n => parseInt(n, 10)))
    .map(d => d.reduce((a, c) => a + c, 0));
};

const part1 = (rawInput) => {
  const input = parseInput(rawInput);

  return Math.max(...input).toString();
};

const part2 = (rawInput) => {
  const input = parseInput(rawInput);
  input.sort((a, b) => b - a);

  return input.slice(0, 3).reduce((a, c) => a + c, 0).toString();
};

run({
  part1: {
    tests: [
      {
        input: `1000
2000
3000

4000

5000
6000

7000
8000
9000

10000`,
        expected: "24000",
      },
    ],
    solution: part1,
  },
  part2: {
    tests: [
      {
        input: `1000
2000
3000

4000

5000
6000

7000
8000
9000

10000`,
        expected: "45000",
      },
    ],
    solution: part2,
  },
  trimTestInputs: true,
  onlyTests: false,
});
