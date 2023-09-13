package com.example.alghorithmvisualizer.presentation.buble_sort

sealed class BubbleSortEvent {
    data class ChangeSpeed(val speedDiff: Float): BubbleSortEvent()
    data class ChangeAmount(val amountDiff: Float): BubbleSortEvent()
    object StartBubbleSort: BubbleSortEvent()
    object StartInsertionSort: BubbleSortEvent()
    object ReGenerateValues: BubbleSortEvent()
    object ToggleStopAlgorithm: BubbleSortEvent()
    object ShuffleList: BubbleSortEvent()
}