package com.example.alghorithmvisualizer.presentation.buble_sort.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.alghorithmvisualizer.presentation.buble_sort.AnimatedIndexesState
import com.example.alghorithmvisualizer.presentation.buble_sort.BubbleNumber
import com.example.alghorithmvisualizer.presentation.ui.theme.AlghorithmVisualizerTheme


@Composable
fun BubbleItem(
    number: BubbleNumber,
    modifier: Modifier = Modifier,
    animatedIndexesState: AnimatedIndexesState,
    isExtracted: Boolean = false,
    animationSpeed: Float
) {
    val offsetY = remember { Animatable(0f) }
    val targetOffset = if (isExtracted) 60f else 0f
    val bgColor by animateColorAsState(
        if (number.index in animatedIndexesState.usedIndexes) {
            if (animatedIndexesState.swappable) {
                Color.Green
            } else MaterialTheme.colorScheme.tertiary
        } else MaterialTheme.colorScheme.primary, label = ""
    )
    LaunchedEffect(isExtracted) {
        offsetY.animateTo(
            targetOffset,
            animationSpec = tween(durationMillis = animationSpeed.toInt())
        )
    }
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(offsetY.value.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(bgColor),
            contentAlignment = Center,

            ) {
            Text(
                text = "${number.number}",
                color = Color.Black
            )
        }
    }


}

@Preview()
@Composable
fun BubbleItemPreview() {
    AlghorithmVisualizerTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            BubbleItem(
                number = BubbleNumber(12, 12),
                animatedIndexesState = AnimatedIndexesState(),
                isExtracted = true,
                animationSpeed = 0f
            )
        }
    }
}