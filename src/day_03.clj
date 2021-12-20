(ns day-03
  (:require [clojure.string :as str]))

(def data-loc "data/03.txt")
(def data (str/split-lines (slurp (do (println "Opening data from" data-loc)
                                      data-loc))))

(defn bit-accumulator [acc [i chr]]
  (let [idx (mod i (count acc))]
    (if (= chr \0)
        acc
        (assoc acc idx (inc (acc idx))))))

(defn init-hash-map-from-data [data]
    (reduce #(assoc %1 %2 0) (sorted-map) (range (count (first data)))))

(defn sum-bits-along-columns [data]
  (let [binary-stream (clojure.string/join "" data)]
    (map last (reduce bit-accumulator
                      (init-hash-map-from-data data)
                      (map-indexed vector binary-stream)))))

(defn binary-collection->decimal [x]
  (Integer/parseInt (apply str x) 2))

(defn bit-sum [data & {:keys [invert] :or {invert false}}]
   (->> (sum-bits-along-columns data)
        (map #(/ % (count data)))
        (map #(if ((if invert < >=) % 1/2) 1 0))))

(defn calc-rate [data & {:keys [rate-type] :or {rate-type "gamma"}}]
  (->>  (if (= rate-type "gamma")
            (bit-sum data)
            (bit-sum data :invert true))
        binary-collection->decimal))

(defn chr->int 
  "Given \0 returns 0"
  [chr] (Character/digit chr 10))

(defn find-carbon-coeff [data]
 (loop [x data y data pos 0]
  (let [x-sum (bit-sum x) 
        x-filtered-by-oxygen (filter #(= (chr->int (nth % pos)) 
                                            (nth x-sum pos))
                                     x)
        y-sum (bit-sum y :invert true)
        y-filtered-by-CO2 (filter #(= (chr->int (nth % pos)) 
                                            (nth y-sum pos))
                                  y)]
    (if (and (= (count x) 1) (= (count y) 1))
      (map binary-collection->decimal [x y])
      (recur 
        (if (= (count x) 1) x x-filtered-by-oxygen)
        (if (= (count y) 1) y y-filtered-by-CO2)
        (inc pos))))))


(println "[part-1] Answer =" (* (calc-rate data :rate-type "gamma")
			  (calc-rate data :rate-type "epsilon")))
(println "[part-2] Answer =" (reduce * (find-carbon-coeff data)))
