package dev.egorand.adventofcode

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {
  @Test fun `Part 1 - Example`() {
    assertEquals(
      150, navigate1(
        commands = listOf(
          "forward 5",
          "down 5",
          "forward 8",
          "up 3",
          "down 8",
          "forward 2",
        )
      )
    )
  }

  @Test fun `Part 1 - Input`() {
    assertEquals(1855814, navigate1(readLines("inputs/day2")))
  }

  @Test fun `Part 2 - Example`() {
    assertEquals(
      900, navigate2(
        commands = listOf(
          "forward 5",
          "down 5",
          "forward 8",
          "up 3",
          "down 8",
          "forward 2",
        )
      )
    )
  }

  @Test fun `Part 2 - Input`() {
    assertEquals(1845455714, navigate2(readLines("inputs/day2")))
  }
}