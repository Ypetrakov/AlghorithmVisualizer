package com.example.alghorithmvisualizer.presentation.buble_sort

import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.alghorithmvisualizer.presentation.buble_sort.components.BubbleItem
import com.example.alghorithmvisualizer.presentation.ui.theme.AlghorithmVisualizerTheme
import kotlin.random.Random
import kotlin.random.nextInt


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BubbleSortScreen(
    onEvent: (BubbleSortEvent) -> Unit,
    list: List<BubbleNumber>,
    animatedIndexesState: AnimatedIndexesState,
    animationSpeed: Float,
    algorithmSpeed: Float,
    numberOfElements: Float,
    isOnStop: Boolean,
    isSorted: Boolean,
    algorithm: String
) {
    Column {
        Row {
            Text(algorithm)
            Button(onClick = {
                onEvent(
                    if (algorithm == "bubble_sort") {
                        BubbleSortEvent.StartBubbleSort
                    }
                    else BubbleSortEvent.StartInsertionSort
                )
            }) {
                Text("Sort")
            }

            Button(onClick = {
                onEvent(BubbleSortEvent.ReGenerateValues)
            }) {
                Text("Generate New Values")
            }
            Button(onClick = {
                onEvent(BubbleSortEvent.ToggleStopAlgorithm)
            }) {
                Text(
                    if (isOnStop) "Resume" else "Stop"
                )
            }
            Button(onClick = {
                onEvent(BubbleSortEvent.ShuffleList)
            }) {
                Text("Shuffle")
            }
        }
        Row {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Number of elements")
                Slider(
                    value = numberOfElements,
                    onValueChange = {onEvent(BubbleSortEvent.ChangeAmount(it))},
                    valueRange = 2f..18f,
                    steps = 15,
                )
                Text(text = numberOfElements.toInt().toString())
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Speed of algorithm")
                Slider(
                    value = algorithmSpeed,
                    onValueChange = {onEvent(BubbleSortEvent.ChangeSpeed(it))},
                    valueRange = 200f..2000f,
                )
                Text(text = algorithmSpeed.toInt().toString())
            }

        }

        Box(
            Modifier.weight(1f).fillMaxWidth(),
            contentAlignment = Alignment.TopCenter,

        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(
                    list,
                    key = { it.index }
                ) {
                    BubbleItem(
                        number = it,
                        isExtracted = it.isExtracted,
                        modifier = Modifier.animateItemPlacement(
                            spring(
                                stiffness = animationSpeed,
                                visibilityThreshold = IntOffset.VisibilityThreshold
                            )
                        ),
                        animatedIndexesState = animatedIndexesState,
                        animationSpeed = animationSpeed
                    )
                }
            }
            if(isSorted) {
                Text(
                    text = "Successfully sorted",
                    modifier = Modifier.align(Alignment.BottomCenter).padding(10.dp)
                )
            }


        }
        


    }
}

data class BubbleNumber(
    var number: Int,
    val index: Int,
    val isExtracted: Boolean = false
)


@Preview(widthDp = 800, heightDp = 360)
@Composable
fun BubbleSortScreenPreview(){
    val bubbleList = List(10) {
        Random.nextInt(1..100)
    }
        .mapIndexed { index, i ->
            BubbleNumber(i, index)
        }

    AlghorithmVisualizerTheme{
        Surface {
            BubbleSortScreen(
                onEvent = { /*TODO*/ },
                list = bubbleList,
                animatedIndexesState = AnimatedIndexesState(),
                animationSpeed = 0f,
                algorithmSpeed = 400f,
                numberOfElements = 10f,
                isOnStop = false,
                isSorted = true,
                algorithm = "Bubble Sort"
            )
        }
    }
}


