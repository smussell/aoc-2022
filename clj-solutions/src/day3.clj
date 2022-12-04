(ns day3
  (:use util)
  (:require [clojure.string :as str])
  (:require [clojure.set :as set]))

(def should-run-mocks false)

(defn process-input [input]
  (->> input
      str/trim
      split-lines))

(defn to-value [ch]
  (let [n (int ch)]
    (if (< n 91) 
      (- n 38) 
      (- n 96))))

(defn split-half [seq] 
  (split-at (/ (count seq) 2) seq))

(defn solve [seq]
  (->> seq
       (map #(map set %))
       (map #(apply set/intersection %))
       (map #(to-value (first %)))
       sum))

(defn part1 [input] 
  (->> input
       process-input
       (map seq)
       (map split-half)
       solve))

(def sample-input "
vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw
")

(run-test part1 "day3Input.txt" should-run-mocks [
  {
    :input sample-input
    :output "157"
  }
])

(defn part2 [input] 
  (->> input
       process-input
       (partition 3)
       solve))

(run-test part2 "day3Input.txt" should-run-mocks [
  {
    :input sample-input
    :output "70"
  }
])
