(ns day6
  (:use util)
  (:require [clojure.string :as str]))

(def should-run-mocks true)

(defn process-input [input]
  (->> input
       str/trim
       seq))

(defn exec [len input]
  (loop [list input 
         ind 0]
    (let [item-set (set (take len list))]
      (if (= (count item-set) len) (+ len ind)
          (recur (rest list) (inc ind))))))

(defn part1 [input] 
  (->> input 
       process-input
       (exec 4)
       debug))

(def sample-input "
mjqjpqmgbljsphdztnvjfqwrcgsmlb
")

(run-test part1 "day6Input.txt" should-run-mocks [
  {
   :input sample-input
   :output "7"
   }
])

(defn part2 [input] 
  (->> input 
       process-input
       (exec 14)
       debug))

(run-test part2 "day6Input.txt" should-run-mocks [
  {
    :input sample-input
    :output "19"
  }
])
