package com.paweljablonski.summary.screens.details.competence_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.paweljablonski.summary.components.TitleSection
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MCompetence
import com.paweljablonski.summary.model.MOutcome
import com.paweljablonski.summary.screens.home.HomeScreenViewModel
import kotlin.Exception




@Composable
fun CompetenceDetailScreen(
    navController: NavController,
    competenceId: String,
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    Scaffold(
    ) {


        val outcomeInfo = produceState<DataOrException<List<MOutcome>,
                Boolean,
                Exception>>(initialValue = DataOrException(data = emptyList(),
            true, Exception(""))){

            value = viewModel.outcomeData.value
        }.value

        androidx.compose.material.Surface(modifier = Modifier
            .fillMaxSize()
            .padding(3.dp)) {
            Column(modifier = Modifier.padding(top = 3.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = CenterHorizontally
                ) {

                if (outcomeInfo.loading == true){
                    LinearProgressIndicator()
                    outcomeInfo.loading = false
                } else{

                    var competenceQuery = emptyList<MCompetence>()
                    if (!viewModel.competenceData.value.data.isNullOrEmpty()){
                        competenceQuery = viewModel.competenceData.value?.data!!.toList()
                            .filter { mCompetence ->
                                mCompetence.competenceId.trim() == competenceId.trim()
                            }
                    }

                    if (competenceQuery.isNotEmpty()){
                        val competence = competenceQuery.first()
                        Text(text = competence.name)
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(text = competence.description)
                    }
                }
            }
        }
    }
}

