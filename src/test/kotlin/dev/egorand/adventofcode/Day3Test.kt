package dev.egorand.adventofcode

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
  @Test fun `Part 1 - Example`() {
    assertEquals(
      198, powerConsumption(
        listOf(
          "00100",
          "11110",
          "10110",
          "10111",
          "10101",
          "01111",
          "00111",
          "11100",
          "10000",
          "11001",
          "00010",
          "01010",
        )
      )
    )
  }

  @Test fun `Part 1 - Input`() {
    assertEquals(3320834, powerConsumption(readLines("inputs/day3")))
  }

  @Test fun `Part 2 - Example`() {
    assertEquals(
      230, lifeSupportRating(
        listOf(
          "00100",
          "11110",
          "10110",
          "10111",
          "10101",
          "01111",
          "00111",
          "11100",
          "10000",
          "11001",
          "00010",
          "01010",
        )
      )
    )
  }

  @Test fun `Part 2 - Input`() {
    assertEquals(4481199, lifeSupportRating(readLines("inputs/day3")))
  }
}
