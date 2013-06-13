# tictactoe

A tic-tac-toe game written in Clojure and CoffeeScript that uses a
min-max algorithm for AI. The app is live at
http://emanuelevans-tictactoe.herokuapp.com/. To run locally, run
`lein run -m tictactoe.web` from this directory (it will serve at
localhost:5000—leiningen must be installed).

Relevant files:

- src/tictactoe/core.clj: The AI for determining the next move.
- test/tictactoe/core_test.clj: Unit tests for the AI.
- src/tictactoe/web.clj: The web server and JSON API.
- resources/public/tictactoe.coffee: The client-side CoffeeScript for
  the tic-tac-toe game.
