(ns day1
  (:use util)
  (:require [clojure.string :as str]))

(def should-run-mocks false)

(defn process-input [input] 
  (->> input 
       str/trim
       split-groups
       (map split-lines) 
       (map parse-ints)
       (map sum)))

(defn part1 [input] 
  (->> input 
       process-input
       (apply max)))

(defn part2 [input] 
  (-> input 
      process-input
      (#(reduce + (->> % sort reverse (take 3))))))

(def sample-input "
1000
2000
3000

4000

5000
6000

7000
8000
9000

10000
")

(run-test part1 "day1Input.txt" should-run-mocks [
  {
   :input sample-input
   :output "24000"
   }
])

(run-test part2 "day1Input.txt" should-run-mocks [
  {
   :input sample-input
   :output "45000"
   }
])
