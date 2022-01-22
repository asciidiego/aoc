(ns day-01
  (:require [clojure.string :as str]))

(defn calculate-depths-increments [depths]
  (reduce (fn [[count depth], val]
            (if (> val depth)
            [(inc count) val]
            [count val]))
          [0 (first depths)]
          depths))

(defn sliding-depths [depths]
  (->> depths
       (partition 3 1)
       (map #(reduce + %))
       calculate-depths-increments))

(defn -main
  ""
  [file-path]
  (println "Loading" file-path)
  (println (->> file-path
            slurp
            str/split-lines
            (map #(Integer/parseInt %))
            calculate-depths-increments)))
