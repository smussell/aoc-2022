(ns util
  (:require [clojure.string :as str]))

(defn debug [val] 
  (do (println val) val))

(defmacro doseq-indexed
  "loops over a set of values, binding index-sym to the 0-based index of each value"
  ([[val-sym values index-sym] & code]
   `(loop [vals# (seq ~values)
           ~index-sym (long 0)]
      (if vals#
        (let [~val-sym (first vals#)]
          ~@code
          (recur (next vals#) (inc ~index-sym)))
        nil))))

;; doesn't understand the doseq-indexed macro
(declare d idx)

(defn run-with-mocks [day-fn test-inputs]
  (doseq-indexed [d test-inputs idx] 
    (let [result (day-fn (:input d))]
      (if (= (:output d) (str result))
        (println "SUCCESS")
        (println (str "FAILED: Expected " (:output d) " got " result))))))

(defn run-test [day-fn input-file should-run-mocks test-inputs]
  (if should-run-mocks
   (run-with-mocks day-fn test-inputs)
   (day-fn (slurp (str "resources/" input-file)))))


;; generic utils

(defn sum [xs]
  (reduce + xs))

(defn split-groups [s]
  (str/split s #"\n\n"))

(defn split-lines [s]
  (str/split s #"\n"))

(defn parse-int [s]
  (Integer/parseInt s))

(defn parse-ints [list]
  (map parse-int list))
