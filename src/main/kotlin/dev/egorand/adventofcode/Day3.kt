package dev.egorand.adventofcode

fun powerConsumption(diagnosticReport: List<String>): Long {
  fun calculateRate(calculateBit: (position: Int) -> Char): Long {
    val rate = buildString {
      for (position in 0..diagnosticReport.first().lastIndex) {
        append(calculateBit(position))
      }
    }
    return rate.toLong(radix = 2)
  }

  val gammaRate = calculateRate { position ->
    mostCommonBitValueAtPosition(diagnosticReport, position)
  }
  val epsilonRate = calculateRate { position ->
    if (mostCommonBitValueAtPosition(diagnosticReport, position) == '0') '1' else '0'
  }
  return gammaRate * epsilonRate
}

private fun mostCommonBitValueAtPosition(
  entries: Iterable<String>,
  position: Int,
): Char {
  val valueCounts = IntArray(2)
  for (entry in entries) {
    valueCounts[if (entry[position] == '1') 1 else 0] += 1
  }
  return when {
    valueCounts[0] > valueCounts[1] -> '0'
    valueCounts[1] > valueCounts[0] -> '1'
    else -> '='
  }
}

fun lifeSupportRating(diagnosticReport: List<String>): Long {
  fun calculateRating(
    entries: MutableSet<String>,
    bitCriteria: (entries: Iterable<String>, position: Int) -> Char,
  ): Long {
    val entryLength = entries.first().length
    var position = 0
    while (entries.size > 1) {
      val bitValue = bitCriteria(entries, position)
      entries.retainAll(entries.filterTo(mutableSetOf()) { it[position] == bitValue })
      position = (position + 1) % entryLength
    }
    return entries.single().toLong(2)
  }

  val oxygenGeneratorRating =
    calculateRating(diagnosticReport.toMutableSet()) { entries, position ->
      mostCommonBitValueAtPosition(entries, position).let {
        if (it == '=') '1' else it
      }
    }
  val co2ScrubberRating = calculateRating(diagnosticReport.toMutableSet()) { entries, position ->
    mostCommonBitValueAtPosition(entries, position).let {
      if (it == '=' || it == '1') '0' else '1'
    }
  }
  return oxygenGeneratorRating * co2ScrubberRating
}
