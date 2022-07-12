package com.paweljablonski.summary.screens.survey

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paweljablonski.summary.model.MQuestion
import com.paweljablonski.summary.navigation.SummaryScreens
import com.paweljablonski.summary.utils.Constants

@SuppressLint("UnrememberedMutableState")
@Composable
fun SurveyScreen(navController: NavController,
    viewModel: SurveyScreenViewModel = hiltViewModel()
){

    val questions = viewModel.questionData.value.data?.toMutableList()
    val questionIndex = remember{
        mutableStateOf(0)
    }

    val answers = viewModel.outcomes


    if (viewModel.questionData.value.loading == true){
        Log.d("QUESTION", "Question loading ...")
    }else{
        val question = try{
            questions?.get(questionIndex.value)
        }catch (ex : Exception){
            null
        }

        if (questions != null){
            QuestionDisplay(question = question!!, questionIndex = questionIndex, viewModel = viewModel){
                if (questionIndex.value == viewModel.getTotalQuestionCount() - 1){
                      viewModel.putOutcomesToFirebase()

                      navController.navigate(SummaryScreens.HomeScreen.name)
                } else {
                    questionIndex.value = questionIndex.value + 1
                }
            }
        }
    }


}
@Composable
fun QuestionDisplay(
    question: MQuestion,
    questionIndex: MutableState<Int>,
    viewModel: SurveyScreenViewModel,
    onNextClicked: (Int) -> Unit = {}){

    val choicesState = remember(question){
        question.choices.toMutableList()
    }

    val answerState = remember(question){
        mutableStateOf<Int?>(null)
    }

    val updateAnswer: (Int) -> Unit = remember(question){
        {
            answerState.value = it
        }
    }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(4.dp),
        color = Constants.mDarkPurple
    ) {
        Column(modifier = Modifier
            .padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            QuestionTracker(counter = questionIndex.value, outOf = viewModel.getTotalQuestionCount())
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp), color = Constants.mLightGray)

            Column {
                Text(text = question.question,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp,
                    color = Constants.mOffWhite,
                    modifier = Modifier
                        .padding(6.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                        .fillMaxHeight(0.1f)
                    )

                choicesState.forEachIndexed { index, map ->
                    Row(modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()

                        .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically) {
                            
                        RadioButton(selected = answerState.value == index, onClick = {
                            updateAnswer(index)
                        },
                            modifier = Modifier
                                .padding(start = 16.dp),
                            colors = RadioButtonDefaults.colors(unselectedColor = Constants.mOffWhite, selectedColor = Constants.mOffWhite)

                        )
                        Text(text = map["description"].toString(), color = Constants.mOffWhite, modifier = Modifier.padding(horizontal = 4.dp), fontSize = 12.sp)
                    }
                }
                Button(onClick = {
                    answerState.value?.let {
                        val choice = choicesState[it]
                        val outcome = mapOf(
                            "competenceId" to question.competenceId,
                            "name" to question.question,
                            "score" to choice["value"],
                            "userId" to "j8ywyVvezAC14xl9d4CW" //todo set id from chosen user
                        )
                        Log.d("OUTCOME", "Chosen value ${outcome.toList().toString()}")

                        viewModel.addOutcome(outcome as Map<String, Any>)
                        onNextClicked(questionIndex.value)
                    }
                },
                    modifier = Modifier
                        .padding(3.dp).align(alignment = Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Constants.mLightBlue
                    )){


                    Text(text = buildAnnotatedString {
                        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)){
                            withStyle(style = SpanStyle(
                                color = Constants.mOffWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp)){

                                if (questionIndex.value != viewModel.getTotalQuestionCount() - 1){
                                    append("Dalej")
                                }else{
                                    append("Zakończ")
                                }
                            }
                        }
                    }, modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}

@Composable
fun QuestionTracker(counter: Int , outOf: Int){
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)){
            withStyle(style = SpanStyle(
                color = Constants.mLightGray,
                fontWeight = FontWeight.Bold,
                fontSize = 27.sp)){

                append("Question ${counter + 1}/")

                withStyle(style = SpanStyle(
                    color = Constants.mLightGray,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp)){
                    append("$outOf")
                }
            }
        }
    },
    modifier = Modifier.padding(20.dp))
}

