#!/usr/bin/env kscript
//DEPS com.squareup.okio:okio-jvm:3.0.0

import okio.FileSystem
import okio.Path.Companion.toPath

val day = args[0].toInt()

with(FileSystem.SYSTEM) {
  write("inputs/day$day".toPath()) {
    writeUtf8("\n")
  }
  write("src/main/kotlin/dev/egorand/adventofcode/Day$day.kt".toPath()) {
    writeUtf8(
      """
      package dev.egorand.adventofcode

      """.trimIndent()
    )
  }
  write("src/test/kotlin/dev/egorand/adventofcode/Day${day}Test.kt".toPath()) {
    writeUtf8(
      """
      package dev.egorand.adventofcode

      import kotlin.test.Test
      import kotlin.test.assertEquals

      class Day${day}Test {
      
      }
      
      """.trimIndent()
    )
  }
}
