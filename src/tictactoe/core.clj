(ns tictactoe.core)

(defn move [board]
  [board (winner board)])

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
