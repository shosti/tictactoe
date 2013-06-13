(ns tictactoe.core-test
  (:require [clojure.test :refer :all]
            [tictactoe.core :refer :all]))

(def x-wins-board
  [[:X :X :X]
   [:O :O nil]
   [:O nil nil]])

(def o-wins-board
  [[nil :X :X]
   [:O :O :O]
   [nil nil :X]])

(def draw-board
  [[:X :X :O]
   [:O :O :X]
   [:X :X :O]])

(def o-about-to-win-board
  [[nil :X :X]
   [:O :O nil]
   [nil nil :X]])

(deftest test-winner
  (testing "No winner"
    (is (= (winner
            [[:X nil :O]
             [:O :O :X]
             [:X nil nil]])
           nil)))

  (testing "X wins horizontally"
    (is (= (winner x-wins-board)
           :X)))

  (testing "O wins horizontally"
    (is (= (winner y-wins-board)
           :O)))

  (testing "O wins diagonally"
    (is (= (winner
            [[:O :X :X]
             [:O :O :X]
             [:X nil :O]])
           :O)))

  (testing "O wins reverse diagonally"
    (is (= (winner
            [[:X :X :O]
             [:X :O :X]
             [:O :X :O]])
           :O)))

  (testing "X wins vertically"
    (is (= (winner
            [[:O :X :O]
             [:X :X nil]
             [nil :X :O]])
           :X)))

  (testing "Draw"
    (is (= (winner draw-board)
           :draw))))

(deftest test-utility
  (testing "O wins"
    (is (= (utility :O) 1)))

  (testing "X wins"
    (is (= (utility :X) -1)))

  (testing "Draw"
    (is (= (utility :draw) 0))))

(deftest test-move
  (testing "X wins"
    (is (= (move x-wins-board)
           [x-wins-board :X])))

  (testing "O wins"
    (is (= (move o-wins-board)
           [o-wins-board :O])))

  (testing "O about to win"
    (is (= (move o-about-to-win-board)
           [o-wins-board :O])))

  (testing "take the double win"
    (is (= (move [[nil :O :X]
                  [nil nil :X]
                  [:X nil :O]])
           [[[nil :O :X]
             [nil :O :X]
             [:X nil :O]] nil]))))
