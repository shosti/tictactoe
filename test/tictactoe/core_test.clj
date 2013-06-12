(ns tictactoe.core-test
  (:require [clojure.test :refer :all]
            [tictactoe.core :refer :all]))

(deftest test-winner
  (testing "No winner"
    (is (= (winner
            [[:X nil :O]
             [:O :O :X]
             [:X nil nil]])
           nil)))

  (testing "Wins horizontally"
    (is (= (winner
            [[:X :X :X]
             [:O :O nil]
             [:O nil nil]])
           :X)))

  (testing "Wins diagonally"
    (is (= (winner
            [[:O :X :X]
             [:O :O :X]
             [:X nil :O]])
           :O)))

  (testing "Wins reverse diagonally"
    (is (= (winner
            [[:X :X :O]
             [:X :O :X]
             [:O :X :O]])
           :O)))

  (testing "Wins vertically"
    (is (= (winner
            [[:O :X :O]
             [:X :X nil]
             [nil :X :O]])
           :X)))

  (testing "Draw"
    (is (= (winner
            [[:X :X :O]
             [:O :O :X]
             [:X :X :O]])
           :draw))))
