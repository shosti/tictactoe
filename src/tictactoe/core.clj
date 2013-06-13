(ns tictactoe.core)

(defn winner [board]
  (let [test-positions
        (concat (for [col (range 3)] [0 col])
                (for [row (range 1 3)] [row 0]))
        directions [[1 0] [0 1] [1 1] [1 -1]]]
    (or
     (first
      (for [pos test-positions
            :let [player (get-in board pos)]
            :when player
            direction directions
            :when (every? #(= (get-in board %) player)
                          (take 3 (iterate #(map + % direction) pos)))]
        player))
     (when (not-any? #(some nil? %) board)
       :draw))))

(defn next-moves [board player]
  (for [row (range 3)
        col (range 3)
        :let [pos [row col]]
        :when (nil? (get-in board pos))]
    (assoc-in board pos player)))

(def human-player :X)
(def computer-player :O)
(def other {:X :O, :O :X})
(def utility
  {computer-player 1
   human-player -1
   :draw 0})

(declare choose-human-move)

(def choose-computer-move
  (memoize
   (fn [board]
     (if-let [board-winner (winner board)]
       [(utility board-winner) board]
       (let [choices (next-moves board computer-player)
             values (map (comp first choose-human-move) choices)]
         (apply max-key first
                (map #(vector %1 %2) values choices)))))))

(def choose-human-move
  (memoize
   (fn [board]
     (if-let [board-winner (winner board)]
       [(utility board-winner) board]
       (let [choices (next-moves board human-player)
             values (map (comp first choose-computer-move) choices)]
         (apply min-key first
                (map #(vector %1 %2) values choices)))))))

(defn move [board]
  (if-let [board-winner (winner board)]
    [board board-winner]
    (let [next-play (second (choose-computer-move board))]
      [next-play (winner next-play)])))
