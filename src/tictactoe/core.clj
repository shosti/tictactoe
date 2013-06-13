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

(def other {:X :O, :O :X})

(defn utility [board-winner player]
  ({player 1
    (other player) -1
    :draw 0} board-winner))

(def choose-next-move
  (memoize
   (fn [board player]
     (if-let [board-winner (winner board)]
       [(utility board-winner player) board]
       (let [choices (next-moves board player)
             values (map
                     (comp - first #(choose-next-move % (other player)))
                     choices)]
         (apply max-key first
                (map #(vector %1 %2) values choices)))))))

(defn move [board]
  (if-let [board-winner (winner board)]
    [board board-winner]
    (let [next-play (second (choose-next-move board :O))]
      [next-play (winner next-play)])))
