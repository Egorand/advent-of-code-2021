package dev.egorand.adventofcode

import dev.egorand.adventofcode.BingoBoard.DrawResult.BoardCompleted
import dev.egorand.adventofcode.BingoBoard.DrawResult.BoardNotCompleted

fun firstBoardToWinScore(configuration: String): Int {
  val (draws, boards) = parseConfiguration(configuration)
  for (draw in draws) {
    for (board in boards.values) {
      val result = board.nextDraw(draw)
      if (result is BoardCompleted) {
        return draw * result.score
      }
    }
  }
  throw AssertionError("None of the boards won!")
}

fun lastBoardToWinScore(configuration: String): Int {
  val (draws, boards) = parseConfiguration(configuration)
  val completedBoardIds = ArrayDeque<Int>()
  var lastBoardToWinDraw = -1
  var lastBoardToWinScore = -1
  for (draw in draws) {
    for (board in boards.values) {
      val result = board.nextDraw(draw)
      if (result is BoardCompleted) {
        completedBoardIds.add(board.id)
        lastBoardToWinDraw = draw
        lastBoardToWinScore = result.score
      }
    }
    while (completedBoardIds.isNotEmpty()) {
      boards.remove(completedBoardIds.removeLast())
    }
  }
  return lastBoardToWinDraw * lastBoardToWinScore
}

private fun parseConfiguration(
  configuration: String
): Pair<List<Int>, MutableMap<Int, BingoBoard>> {
  val parts = configuration.split("\n\n")
  val draws = parts.first().split(',').map { it.toInt() }
  val boards = LinkedHashMap<Int, BingoBoard>()
  for (boardId in 1..parts.lastIndex) {
    boards[boardId] = BingoBoard.fromString(parts[boardId], boardId)
  }
  return draws to boards
}

class BingoBoard private constructor(
  val id: Int,
  private val numberToRows: MutableMap<Int, MutableSet<Int>>,
  private val numberToCols: MutableMap<Int, MutableSet<Int>>,
  private var totalNumbersSum: Int,
) {
  private val remainingNumbersPerRow = IntArray(5) { 5 }
  private val remainingNumbersPerCol = IntArray(5) { 5 }

  fun nextDraw(draw: Int): DrawResult {
    val rows = numberToRows.remove(draw) ?: return BoardNotCompleted
    val cols = numberToCols.remove(draw) ?: return BoardNotCompleted
    totalNumbersSum -= draw
    for (row in rows) {
      remainingNumbersPerRow[row] -= 1
      if (remainingNumbersPerRow[row] == 0) return BoardCompleted(totalNumbersSum)
    }
    for (col in cols) {
      remainingNumbersPerCol[col] -= 1
      if (remainingNumbersPerCol[col] == 0) return BoardCompleted(totalNumbersSum)
    }
    return BoardNotCompleted
  }

  sealed class DrawResult {
    data class BoardCompleted(val score: Int) : DrawResult()
    object BoardNotCompleted : DrawResult()
  }

  companion object {
    fun fromString(
      str: String,
      boardId: Int,
    ): BingoBoard {
      val numberToRows = mutableMapOf<Int, MutableSet<Int>>()
      val numberToCols = mutableMapOf<Int, MutableSet<Int>>()
      var totalNumbersSum = 0
      str.lines().forEachIndexed { lineIndex, line ->
        val numbersInLine = line.trim().split(Regex("\\s+")).filter { it.isNotBlank() }
        numbersInLine.forEachIndexed { colIndex, number ->
          val number = number.toInt()
          numberToRows.putIfAbsent(number, mutableSetOf(lineIndex))?.add(lineIndex)
          numberToCols.putIfAbsent(number, mutableSetOf(colIndex))?.add(colIndex)
          totalNumbersSum += number
        }
      }
      return BingoBoard(boardId, numberToRows, numberToCols, totalNumbersSum)
    }
  }
}
