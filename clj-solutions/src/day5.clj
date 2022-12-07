(ns day5
  (:use util)
  (:require [clojure.string :as str]))

(def should-run-mocks false)

(defn process-input [input]
  (->> input
       (#(str/split % #"\n\n"))))

(defn transpose [m]
  (apply mapv vector m))

(defn convert-row [row-str] 
  (->> row-str
       (partition-all 4)
       (map #(filter (fn [c] (not= \space c)) %))))

(defn extract-group [groups]
  (->> groups
       (filter not-empty)
       (map #(nth % 1))))

(defn init [input] 
  (->> input
       split-lines
       drop-last
       (filter not-empty)
       (map convert-row)
       transpose
       (map extract-group)))

(defn parse-move [line] 
  (->> line 
       (re-matches #"move ([0-9]+) from ([0-9]+) to ([0-9]+)")
       (drop 1)
       (map parse-int)))

(defn exec-move [stack [n from target]]
  (let [[head tail] (split-at n (nth stack (- from 1)))
        updated-stack (assoc stack (- from 1) tail)
        appended (concat (reverse head) (nth stack (- target 1)))]
    (assoc updated-stack (- target 1) appended)))

(defn part1 [input] 
  (let [[initial changes] (process-input input)
        stacks (init initial)]
    (->> changes
         split-lines
         (map parse-move)
         (reduce exec-move (vec stacks))
         (map first)
         (apply str))))

(def sample-input "
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
")

(run-test part1 "day5Input.txt" should-run-mocks [
  {
    :input sample-input
    :output "CMZ"
  }
])

;; not great too much copy paste, but want to get this done
(defn exec-move [stack [n from target]]
  (let [[head tail] (split-at n (nth stack (- from 1)))
        updated-stack (assoc stack (- from 1) tail)
        appended (concat head (nth stack (- target 1)))]
    (assoc updated-stack (- target 1) appended)))

(defn part2 [input]
  (let [[initial changes] (process-input input)
        stacks (init initial)]
    (->> changes
         split-lines
         (map parse-move)
         (reduce exec-move (vec stacks))
         (map first)
         (apply str))))

(run-test part2 "day5Input.txt" should-run-mocks [
  {
    :input sample-input
    :output "MCD"
  }
])
