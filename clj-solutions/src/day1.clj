
(ns day1
  (:use util)
  (:require [clojure.string :as str]))

(defn parse-int [s]
  (Integer. (re-find  #"\d+" s)))

(def should-run-mocks true)

(defn integers [str] 
  (map parse-int (str/split str #"\n")))

(defn process-input [input] 
  (let [groups (-> input str/trim (.split "\n\n"))] 
    (map #(reduce + (integers %)) groups)))

(defn part1 [input] 
  (->> input 
       process-input
       (apply max)
       ))

«(defn part2 [input] 
  (-> input 
      process-input
      (#(reduce + (->> % sort reverse (take 3))))
    ))

(run-test part1 "day1Input.txt" should-run-mocks [
  {
   :input "
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
"
   :output "24000"
   }
])

(run-test part2 "day1Input.txt" should-run-mocks [
  {
   :input "
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
"
   :output "45000"
   }
])
