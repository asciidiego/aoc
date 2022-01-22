(ns day-04
  (:require
   [clojure.string :as str]))

(def data (->> (slurp "./data/04.txt")
               str/split-lines
               (remove #(= "" %))))

(def hand (->> data
               first
               (#(str/split % #","))
               (mapv #(Integer/parseInt %))))

(def boards (->> data
                 rest ;; first line is the hand; we can skip it!
                 (partition 5 5)
                 (mapv (fn [board]
                         (mapv (fn [row-as-str]
                                 (->> (str/split row-as-str #" +")
                                      (remove #(= % ""))
                                      (map #(Integer/parseInt %))
                                      vec))
                               board)))))

(defn find-number-in-board
  "Given a board and a number, return the coordinates/location of the number if it
  is found in the board. Otherwise, return nil."
  [board number]
  (first (for [[row-num row] (map-indexed vector board)
               :let [match-col (.indexOf row number)]
               :when (not= match-col -1)]
           [row-num match-col])))

(defn get-victory-line
  "If there is a victory line (coordinates for all the marked numbers), return it;
  otherwise return nil"
  [timeline]
  (letfn [(coordinate-victory? [coordinate-fn]
            (->> (sort-by coordinate-fn timeline)
                 (partition-by coordinate-fn)
                 (filter #(= (count %) 5))
                 first))]
    (or (coordinate-victory? first) (coordinate-victory? second))))

(defn play-bingo
  "Given some bingo boards and a sequence of numbers, play bingo until a winner is
  found. Then, return the victory line and the number that won."
  ;; TODO: Reduce hand and then calculate each new state for each board instead
  ;; of the other way around. That way, we will not the hand index and looking
  ;; for the min-key to get the earliest winner.
  [boards hand]
  (apply min-key :winning-turn
         (map (fn [board]
                (letfn [(update-coordinates [coordinates [index number]]
                          (if-let [match (find-number-in-board board number)]
                            (let [new-coordinates (conj coordinates match)]
                              (if-let [victory-line (get-victory-line new-coordinates)]
                                (reduced {:winning-turn index
                                          :victory-line victory-line
                                          :marked-coordinates new-coordinates
                                          :winning-hand number
                                          :board board})
                                new-coordinates))
                            coordinates))
                        (init-coordinate-vector [] [])]
                  (reduce update-coordinates (init-coordinate-vector) (map-indexed vector hand))))
              boards)))

(defn negate-coordinates
  "Return a list of unmarked coordinates given a sequence of coordinates"
  [coordinates]
  (for [i (range 5)
        j (range 5)
        :when (not (some #(= % [i j]) coordinates))]
    [i j]))

(defn main []
  (let [{:keys [marked-coordinates
                board
                winning-hand]} (play-bingo boards hand)
        unmarked-coordinates (negate-coordinates marked-coordinates)
        coordinate->value (fn [[i j]]
                            (nth (nth board i) j))]
    (* winning-hand
       (reduce + (map coordinate->value unmarked-coordinates)))))
