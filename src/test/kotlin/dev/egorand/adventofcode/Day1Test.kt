package dev.egorand.adventofcode

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
  @Test fun `Part 1 - Example`() {
    assertEquals(
      7, depthMeasurementIncreases(
        depthMeasurements = listOf(
          199,
          200,
          208,
          210,
          200,
          207,
          240,
          269,
          260,
          263,
        )
      )
    )
  }

  @Test fun `Part 1 - Input`() {
    assertEquals(1715, depthMeasurementIncreases(depthMeasurements = readInts("inputs/day1")))
  }

  @Test fun `Part 2 - Example`() {
    assertEquals(
      5, depthMeasurementWindowIncreases(
        depthMeasurements = listOf(
          199,
          200,
          208,
          210,
          200,
          207,
          240,
          269,
          260,
          263,
        ),
        windowSize = 3,
      )
    )
  }

  @Test fun `Part 2 - Input`() {
    assertEquals(
      1739,
      depthMeasurementWindowIncreases(depthMeasurements = readInts("inputs/day1"), windowSize = 3)
    )
  }
}
