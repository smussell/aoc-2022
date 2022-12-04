(ns day4
  (:use util)
  (:require [clojure.string :as str]))

(def should-run-mocks false)

(defn split-row [row]
  (->> row
       (#(str/split % #","))
       (map #(str/split % #"-"))
       (map #(map parse-int %))))

(defn contains [[[a1 a2] [b1 b2]]]
  (and (>= b1 a1) (<= b2 a2)))

(defn overlaps [[[a1 a2] [b1 b2]]]
  (or 
   (and (>= b1 a1) (<= b1 a2))
   (and (>= b2 a1) (<= b2 a2))))

(defn process-input [input]
  (->> input
       str/trim
       split-lines
       (map #(split-row %))))

(defn part1 [input] 
  (->> input 
       process-input
       (map #(or (contains %) (contains (reverse %))))
       (filter identity)
       count))

(def sample-input "
2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8
")

(run-test part1 "day4Input.txt" should-run-mocks [
  {
    :input sample-input
    :output "2"
  }
])

(defn part2 [input] 
  (->> input 
       process-input
       (map #(or (overlaps %) (overlaps (reverse %))))
       (filter identity)
       count))

(run-test part2 "day4Input.txt" should-run-mocks [
  {
    :input sample-input
    :output "4"
  }
])
