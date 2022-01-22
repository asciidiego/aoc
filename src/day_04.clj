(ns day-04
  (:require [clojure.string :as str]))

(def raw-data (slurp "./data/04.txt"))
(def data (->> raw-data
               str/split-lines
               (remove #(= "" %))))

(def hand (->> data
               first
               (#(str/split % #","))))

(def boards (->> data
                 rest ;; first line is the hand; we can skip it!
                 (partition 5 5)
                 (mapv (fn [board]
                         (mapv (fn [row-as-str]
                                 (->> (str/split row-as-str #" +")
                                      (remove #(= % ""))
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

(defn run-number-through-board
  "Given a board-timeline pair and a hand number it appends the coordinates of the
  number if found on the board. Otherwise, it returns the input timeline as is.
  "
  [[board timeline] hand-number]
  (if-let [match (find-number-in-board board hand-number)]
    (conj timeline match)
    timeline))

;; The first line of the data is the hands that are being drawn
;; After that, each new board represents a bingo board.
;;
;; - [x] 1st Problem -> Get the hands as a vector

;; - [x] 2nd Problem -> Get all the boards

;; - [ ] 3rd (and last) problem -> how to compute a bingo victory?
;; hmmm... probably need to cut this problem a bit more... reduce
;; the scope of it.
;;
;; - We need to find a way to *mark* positions in a board.
;; - After we have done that, we need to mark a victory by a horizontal line
;; - After we have done that, we need to mark a victory by a vertical line
;;
;;   - [ ] Find a way to mark position in board after each hand drawn
;;
;;     - We have already parsed our boards and, thanks to Clojure, we can easily
;;       access rows and columns. For example, to access the first board and the
;;       the first row and the last column, we would do the following!
;;
;; - [x] Parse each board row to be able to easily access rows and columns. To
;;       do this, we simply split by spaces.
;;
;; - For each hand number (e.g. 7), we have to find it on each of the boards. If
;;   there is a match, in any of the boards, we have to mark the number location
;;   for that board. We repeat this process for all the boards and for all the
;;   hand numbers drawn. Then, calculating whether a victory has taken place or
;;   not it's a matter of, after calculating all marked positions for all
;;   boards, see if one has 5 marks in successive order in terms of either rows
;;   or columns.
;;
;;
;;    - ASSUMPTION! -> A number may appear once and only once in a given board.
;;      Thus, if a given number is found when traversing the board, you can stop
;;      the traversal.

;; Next idea -> Marking Processor. Given a timeline (empty vector), process a
;; sequence of hands and store the marked numbers in order of occurence. For
;; example, if the hand is [2 3 7 8], and the number 3 and 8 are in the (0, 1)
;; and (0, 2) positions, return the following [(0, 1) (0, 2)].
;;
;; It is worth thinking of how to process this board by board for each hand
;; step. As we cannot process each board independently. We have, for each hand
;; drawn, process all the boards and store their respective timelines, then we
;; process the next hand number and we keep doing that until there is a winner
;; among all the boards.
;;
;; In other words, create a function that given a timeline, a board, and a hand,
;; returns an updated timeline. If there was a match, it should be appended to
;; the end of the timeline; otherwise, return the previous timeline.

;; One of the final tasks is to determine whether, given a board timeline, the
;; board has won the match. It involves detecting a victory by horizontal match
;; and victory by vertical match.
