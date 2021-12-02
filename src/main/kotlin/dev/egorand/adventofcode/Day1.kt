package dev.egorand.adventofcode

fun depthMeasurementIncreases(depthMeasurements: List<Int>): Int {
  return depthMeasurements
    .drop(1)
    .mapIndexed { index, measurement ->
      if (measurement > depthMeasurements[index]) 1 else 0
    }
    .sum()
}

fun depthMeasurementWindowIncreases(
  depthMeasurements: List<Int>,
  windowSize: Int,
): Int {
  var increases = 0
  var currentWindowSum = depthMeasurements.take(windowSize).sum()
  var index = windowSize
  while (index < depthMeasurements.size) {
    val nextWindowSum = currentWindowSum - depthMeasurements[index - windowSize] +
      depthMeasurements[index]
    if (nextWindowSum > currentWindowSum) increases += 1
    currentWindowSum = nextWindowSum
    index += 1
  }
  return increases
}
