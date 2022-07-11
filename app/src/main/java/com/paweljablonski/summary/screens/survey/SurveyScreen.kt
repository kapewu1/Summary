package com.paweljablonski.summary.screens.survey

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SurveyScreen(navController: NavController,
    viewModel: SurveyScreenViewModel = hiltViewModel()
){
    Text("Survey")

    Questions(viewModel)


}

@Composable
fun Questions(viewModel: SurveyScreenViewModel){
    val questions = viewModel.questionData.value.data?.toMutableList()

    if (viewModel.questionData.value.loading == true){
        Log.d("QUESTION", "Question loading ...")
    }else{
        Log.d("QUESTION", "Question stopped.")
        questions?.forEach {
            Log.d("QUESTION", "Question : $it")
        }
    }
}