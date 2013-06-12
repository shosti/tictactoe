(ns tictactoe.core)

(defn move [board]
  [board (winner board)])

(defn winner [board]
  (defn board-get [[row col]]
    (get (get board row) col))

  (let [test-positions
        (concat (for [col (range 3)] [0 col])
                (for [row (range 1 3)] [row 0]))
        directions [[1 0] [0 1] [1 1] [1 -1]]]
    (or
     (first
      (for [pos test-positions
            :let [player (board-get pos)]
            :when player
            direction directions
            :let [next-pos (map + pos direction)]
            :when (= player
                     (board-get next-pos)
                     (board-get (map + next-pos direction)))]
        player))
     (when (not-any? #(some nil? %) board)
       :draw))))
