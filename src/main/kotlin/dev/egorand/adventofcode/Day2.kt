package dev.egorand.adventofcode

private val commandRegex = Regex("^(forward|down|up) (\\d)$")

private fun parseCommand(command: String): Pair<String, Long> {
  return commandRegex.matchEntire(command)!!.destructured.let { (command, arg) ->
    command to arg.toLong()
  }
}

fun navigate1(commands: List<String>): Long {
  var position = 0L
  var depth = 0L
  for (command in commands) {
    val (command, arg) = parseCommand(command)
    when (command) {
      "forward" -> position += arg
      "down" -> depth += arg
      "up" -> depth -= arg
    }
  }
  return position * depth
}

fun navigate2(commands: List<String>): Long {
  var position = 0L
  var depth = 0L
  var aim = 0L
  for (command in commands) {
    val (command, arg) = parseCommand(command)
    when (command) {
      "forward" -> {
        position += arg
        depth += aim * arg
      }
      "down" -> aim += arg
      "up" -> aim -= arg
    }
  }
  return position * depth
}
