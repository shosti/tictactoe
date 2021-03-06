(defproject tictactoe "1.0.0-SNAPSHOT"
  :description "A tic-tac-toe webapp"
  :url "http://emanuelevans-tictactoe.herokuapp.com"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.1"]
                 [ring/ring-jetty-adapter "1.1.0"]
                 [ring/ring-devel "1.1.0"]
                 [environ "0.2.1"]
                 [cheshire "5.2.0"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.2.1"]]
  :hooks [environ.leiningen.hooks]
  :profiles {:production {:env {:production true}}})
