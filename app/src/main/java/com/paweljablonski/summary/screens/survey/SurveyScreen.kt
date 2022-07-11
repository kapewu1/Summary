package com.paweljablonski.summary.screens.survey

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paweljablonski.summary.model.MQuestion
import com.paweljablonski.summary.utils.Constants

@Composable
fun SurveyScreen(navController: NavController,
    viewModel: SurveyScreenViewModel = hiltViewModel()
){

    val questions = viewModel.questionData.value.data?.toMutableList()
//
    if (viewModel.questionData.value.loading == true){
        Log.d("QUESTION", "Question loading ...")
    }else{

        if (questions != null){
            QuestionDisplay(question = questions.first())
        }
//        Log.d("QUESTION", "Question stopped.")
//        questions?.forEach {
//            Log.d("QUESTION", "Question : $it")
//        }
    }

//    QuestionDisplay()
//    Questions(viewModel)


}
@Composable
fun QuestionDisplay(
    question: MQuestion,
//    questionIndex: MutableState<Int>,
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
            QuestionTracker()
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

                Button(onClick = {},
                    modifier = Modifier
                        .padding(3.dp).align(alignment = Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Constants.mLightBlue
                    )){
                    Text("ds")
                }
            }
        }
    }
}

@Composable
fun QuestionTracker(counter: Int = 10, outOf: Int = 100){
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)){
            withStyle(style = SpanStyle(
                color = Constants.mLightGray,
                fontWeight = FontWeight.Bold,
                fontSize = 27.sp)){

                append("Question $counter/")

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

//@Composable
//fun Questions(viewModel: SurveyScreenViewModel){
//    val questions = viewModel.questionData.value.data?.toMutableList()
//
//    if (viewModel.questionData.value.loading == true){
//        Log.d("QUESTION", "Question loading ...")
//    }else{
//        Log.d("QUESTION", "Question stopped.")
//        questions?.forEach {
//            Log.d("QUESTION", "Question : $it")
//        }
//    }
//}