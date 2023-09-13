package com.example.alghorithmvisualizer.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.alghorithmvisualizer.presentation.alghorithm_choice.AlgorithmChoiceScreen
import com.example.alghorithmvisualizer.presentation.buble_sort.BubbleSortScreen
import com.example.alghorithmvisualizer.presentation.buble_sort.BubbleSortViewModel

fun NavGraphBuilder.mainScreen(
    navController: NavController
) {
    composable("main_screen") {
        AlgorithmChoiceScreen(
            onNavigateToBubbleAlgorithm = {
                navController.navigateToBubbleScreen("bubble_sort")
            },
            onNavigateToInsertionAlgorithm = {
                navController.navigateToBubbleScreen("insertion_sort")
            }
        )
    }
}

fun NavController.navigateToBubbleScreen(algorithm: String) {
    this.navigate("bubble_screen/$algorithm")
}


fun NavGraphBuilder.bubbleScreen(
) {
    composable(
        "bubble_screen/{algorithmType}",
        arguments = listOf(navArgument("algorithmType") { type = NavType.StringType})
    ) {
        val bubbleSortViewModel = viewModel<BubbleSortViewModel>()
        BubbleSortScreen(
            onEvent = bubbleSortViewModel::onEvent,
            animatedIndexesState = bubbleSortViewModel.animatedIndexesState,
            list = bubbleSortViewModel.list,
            algorithmSpeed = bubbleSortViewModel.algorithmSpeed,
            animationSpeed = bubbleSortViewModel.animationSpeed,
            numberOfElements = bubbleSortViewModel.amount,
            isOnStop = bubbleSortViewModel.onStop,
            isSorted = bubbleSortViewModel.isSorted,
            algorithm = it.arguments?.getString("algorithmType") ?: "BubbleSort"
        )
    }
}