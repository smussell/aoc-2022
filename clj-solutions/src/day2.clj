(ns day2
  (:use util)
  (:require [clojure.string :as str]
            [clojure.core.match :refer [match]]))

(def should-run-mocks false)

(defn process-input [input]
  (->> input
      str/trim
      split-lines
      (map #(str/split % #" ")))) 

;; A for Rock, B for Paper, and C for Scissors
;; X for Rock, Y for Paper, and Z for Scissors.
;; 1 for Rock, 2 for Paper, and 3 for Scissors 
(defn result [round] 
  (case round
    (["A" "X"] ["B" "Y"] ["C" "Z"]) 3
    (["A" "Y"] ["B" "Z"] ["C" "X"]) 6
    0))

(defn val-map [[_ s]]
  (case s
    "X" 1
    "Y" 2
    "Z" 3))

(defn part1 [input] 
  (->> input
       process-input
       (map #(+ (result %) (val-map %)))
       sum))

(def test-input-one "
A Y
B X
C Z
")

(run-test part1 "day2Input.txt" should-run-mocks [
  {
    :input test-input-one
   :output "15"
  }
])

;; A for Rock, B for Paper, and C for Scissors
;; 1 for Rock, 2 for Paper, and 3 for Scissors 
;; X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win
(defn infer-points [round]
  (case round
    (["A" "Y"] ["B" "X"] ["C" "Z"]) 1
    (["A" "Z"] ["B" "Y"] ["C" "X"]) 2
    (["A" "X"] ["B" "Z"] ["C" "Y"]) 3))

(defn val-map-2 [[_ s]]
  (case s
    "X" 0
    "Y" 3
    "Z" 6))

(defn part2 [input]
  (->> input
       process-input
       (map #(+ (infer-points %) (val-map-2 %)))
       sum))

(def test-input-two "
A Y
B X
C Z
")

(run-test part2 "day2Input.txt" should-run-mocks [
  {
    :input test-input-two
    :output "12"
  }
])
