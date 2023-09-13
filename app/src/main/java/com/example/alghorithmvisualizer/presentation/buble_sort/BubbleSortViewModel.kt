package com.example.alghorithmvisualizer.presentation.buble_sort

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

class BubbleSortViewModel : ViewModel() {

    private val _list = mutableStateListOf<BubbleNumber>().apply {
        addAll(
            generateValues()
        )
    }
    private val range = 1..100
    var algorithmSpeed by mutableFloatStateOf(400f)
        private set

    val animationSpeed by derivedStateOf {
        160000 / algorithmSpeed
    }
    val list: List<BubbleNumber> = _list
    var animatedIndexesState by mutableStateOf(AnimatedIndexesState())
        private set
    private var algorithmJob: Job? = null

    var onStop by mutableStateOf(false)
        private set

    var amount by mutableFloatStateOf(10f)
        private set

    var isSorted by mutableStateOf(false)
        private set

    private fun bubbleSort() {
        stopJob()
        isSorted = false

        algorithmJob = viewModelScope.launch {
            var swapped: Boolean
            do {
                swapped = false
                for (i in 1 until _list.size) {
                    while (onStop) {
                        delay(100)
                    }
                    animatedIndexesState = AnimatedIndexesState(usedIndexes = listOf(_list[i - 1].index, _list[i].index))
                    delay((animationSpeed * 1.25).toLong())
                    if (_list[i - 1].number > _list[i].number) {
                        while (onStop) {
                            delay(100)
                        }
                        swapped = true
                        animatedIndexesState = animatedIndexesState.copy(swappable = true)
                        delay((animationSpeed * 0.5).toLong())
                        val temp = _list[i - 1]
                        _list[i - 1] = _list[i]
                        _list[i] = temp
                        delay((animationSpeed * 1.75).toLong())
                    }
                }
            } while (swapped)
            isSorted = true
            animatedIndexesState = AnimatedIndexesState()
        }
    }

    private fun insertionSort() {
        stopJob()
        isSorted = false

        algorithmJob = viewModelScope.launch {
            for (i in 1 until _list.size) {
                val key = _list[i].number
                var j = i - 1
                _list[i] = _list[i].copy(isExtracted = true)
                animatedIndexesState = AnimatedIndexesState(usedIndexes = listOf(_list[i].index))
                delay((animationSpeed*1.5).toLong())
                while (onStop) {
                    delay(100)
                }
                //animatedIndexesState = AnimatedIndexesState(usedIndexes = listOf(j, j+1))
                while (j >= 0 && key < _list[j].number) {
                    animatedIndexesState = AnimatedIndexesState(usedIndexes = listOf(_list[j].index, _list[j+1].index), swappable = true)
                    delay((animationSpeed*1.5).toLong())
                    val temp = _list[j+1]
                    _list[j+1] = _list[j]
                    _list[j] = temp
                    //_list[j + 1] = _list[j+1].copy(number = _list[j].number)
                    j -= 1
                    delay((animationSpeed).toLong())
                    animatedIndexesState = animatedIndexesState.copy(swappable = false)
                    delay((animationSpeed).toLong())

                    while (onStop) {
                        delay(100)
                    }

                }
                _list[j+1] = _list[j+1].copy(isExtracted = false)
                delay((animationSpeed*1.5).toLong())
            }
            isSorted = true
            animatedIndexesState = AnimatedIndexesState()
        }
    }

    private fun generateValues(size: Int = 10, range: IntRange = 1..100): List<BubbleNumber> =
        List(size) {
            Random.nextInt(range = range)
        }.mapIndexed { index, i ->
            BubbleNumber(i, index)
        }


    fun onEvent(bubbleSortEvents: BubbleSortEvent) {
        when (bubbleSortEvents) {
            is BubbleSortEvent.ChangeAmount -> {
                resetExtraction()
                stopJob()
                amount = bubbleSortEvents.amountDiff
                _list.apply {
                    clear()
                    addAll(generateValues(size = amount.toInt()))
                }
            }

            is BubbleSortEvent.ChangeSpeed -> algorithmSpeed = bubbleSortEvents.speedDiff
            is BubbleSortEvent.StartBubbleSort -> bubbleSort()
            is BubbleSortEvent.StartInsertionSort -> insertionSort()
            is BubbleSortEvent.ReGenerateValues -> {
                stopJob()
                val updatedList = _list.map {
                    it.copy(number = Random.nextInt(range), isExtracted = false)
                }.toList()
                _list.clear()
                _list.addAll(updatedList)
            }

            is BubbleSortEvent.ToggleStopAlgorithm -> {
                onStop = !onStop
            }

            is BubbleSortEvent.ShuffleList -> {
                _list.shuffle()
                resetExtraction()
                stopJob()
            }
        }
    }

    private fun stopJob() {
        algorithmJob?.cancel()
        animatedIndexesState = AnimatedIndexesState()
        isSorted = false
    }

    private fun resetExtraction() {
        val extractedIndex = _list.indexOfFirst{ it.isExtracted}
        if (extractedIndex == -1) return
        _list[extractedIndex] = _list[extractedIndex].copy(isExtracted = false)
    }
}

data class AnimatedIndexesState(
    val usedIndexes: List<Int> = listOf(),
    val swappable: Boolean = false
)