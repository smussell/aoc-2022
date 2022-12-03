import {existsSync, writeFileSync, statSync} from 'fs';
import fetch from 'node-fetch';
import path from 'path';
import * as url from 'url';
import dotenv from 'dotenv';

dotenv.config();

const __dirname = url.fileURLToPath(new URL('.', import.meta.url));

const USER_AGENT_HEADER = {
  "User-Agent": "github.com/caderek/aocrunner by maciej.caderek@gmail.com"
}

const template = (day) =>  `(ns day${day}
  (:use util)
  (:require [clojure.string :as str]))

(def should-run-mocks true)

(defn process-input [input]
  (->> input
      str/trim
      split-lines))

(defn part1 [input] 
  (->> input 
       process-input))

(def test-input-one "

")

(run-test part1 "day${day}Input.txt" should-run-mocks [
  {
    :input test-input-one
    :output ""
  }
])

(defn part2 [input] 
  (->> input 
       process-input))

(def test-input-two "

")

(run-test part2 "day${day}Input.txt" should-run-mocks [
  {
    :input test-input-two
    :output ""
  }
])
`;

const filePath = (day) => path.join(__dirname, '../resources', `day${day}Input.txt`);

const fetchInput = async (day) => {
  const API_URL = "https://adventofcode.com";
  const inputFilePath = filePath(day);

  if (existsSync(inputFilePath) && statSync(inputFilePath).size > 0) {
    console.warn(`INPUT FOR AOC ${process.env.YEAR} DAY ${day} ALREADY FETCHED`);
    return
  }

  try {
    const input = await fetch(`${API_URL}/${process.env.YEAR}/day/${day}/input`, {
      headers: {
        cookie: `session=${process.env.AOC_SESSION_KEY}`,
        ...USER_AGENT_HEADER
      }
    });
    writeFileSync(inputFilePath, await input.text());
  } catch(e) {
    console.error(e);
  }
}

const main = () => {
  if(process.argv.length === 3) {
    const dayNum = process.argv[2];
    fetchInput(dayNum);
    const content = template(dayNum);
    writeFileSync(path.join(__dirname, '../src', `day${dayNum}.clj`), content);
    console.log(`Created day${dayNum}.clj and day${day}Input.txt`)
  } else {
    console.log("Usage: npm start <daynum>")
  }
}

main();


