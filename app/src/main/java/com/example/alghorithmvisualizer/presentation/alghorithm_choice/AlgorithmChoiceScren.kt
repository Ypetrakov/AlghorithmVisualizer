package com.example.alghorithmvisualizer.presentation.alghorithm_choice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alghorithmvisualizer.presentation.alghorithm_choice.components.Algorithm
import com.example.alghorithmvisualizer.presentation.ui.theme.AlghorithmVisualizerTheme


@Composable
fun AlgorithmChoiceScreen(
    onNavigateToBubbleAlgorithm: () -> Unit,
    onNavigateToInsertionAlgorithm: () -> Unit,

    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "Choose algorithm to visualize",
            modifier = Modifier.align(Alignment.TopCenter).padding(PaddingValues(top = 40.dp)),
        )
        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ){
            Algorithm(
                text = "Bubble Sort",
                modifier = Modifier.height(65.dp),
                onClick = { onNavigateToBubbleAlgorithm() }
            )
            Algorithm(
                text = "Insertion Sort",
                modifier = Modifier.height(65.dp),
                onClick = { onNavigateToInsertionAlgorithm() }
            )
        }
    }
}

@Preview
@Composable
fun AlgorithmChoiceScreenPreview(

) {
    AlghorithmVisualizerTheme {
        AlgorithmChoiceScreen(
            onNavigateToBubbleAlgorithm = {},
            onNavigateToInsertionAlgorithm = {}
        )
    }

}