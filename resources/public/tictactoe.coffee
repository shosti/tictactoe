$ ->
  game = new Game
  $('td').click (e) ->
    [row, col] = @id.split('').map((x) -> parseInt(x, 10))
    game.move(row, col)

class Game
  constructor: (@player = 'X') ->
    @board =
      [[null, null, null],
       [null, null, null],
       [null, null, null]]
    @isHumanTurn = (@player == 'X')
    @drawBoard()

  move: (row, col) ->
    if @isHumanTurn and not @board[row][col]
      @board[row][col] = @player
      @isHumanTurn = false
      @drawBoard()

      $.ajax
        url: 'move'
        data: {board: JSON.stringify(@board)}
        success: (data) =>
          @board = JSON.parse(data)
          @isHumanTurn = true
          @drawBoard()
        error: (xhr, status, error) ->
          $('#status').text("Error: #{error}")

  drawBoard: ->
    for row in [0..2]
      for col in [0..2]
        id = "##{row}#{col}"
        $(id).text(@board[row][col] || '')

    $('#status').text(if @isHumanTurn then 'Your turn' else "Computer's turn")