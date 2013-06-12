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
      @requestComputerMove()

  requestComputerMove: ->
    $.ajax
      url: 'move'
      data: {board: JSON.stringify(@board)}
      success: @handleComputerMove
      error: (xhr, status, error) ->
        $('#status').text("Error: #{error}")

  handleComputerMove: (dataStr) =>
    data = JSON.parse(dataStr)
    if data.winner
      @drawBoard()
      statusText =
        if data.winner == 'draw'
        then "It's a draw!"
        else "#{data.winner} wins!"
      $('#status').text(statusText)
    else
      @board = data.board
      @isHumanTurn = true
      @drawBoard()

  drawBoard: ->
    for row in [0..2]
      for col in [0..2]
        id = "##{row}#{col}"
        $(id).text(@board[row][col] || '')

    statusText = if @isHumanTurn then 'Your turn' else "Computer's turn"
    $('#status').text(statusText)