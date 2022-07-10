package com.paweljablonski.summary.screens.home
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.paweljablonski.summary.components.*
import com.paweljablonski.summary.model.MOutcome
import com.paweljablonski.summary.model.MUser
import com.paweljablonski.summary.navigation.SummaryScreens


@Composable
fun Home(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()

) {
    Scaffold (
        topBar = {
            SummaryAppBar("Summary", navController = navController) //todo Check if logged profile is administrator one
        },
        floatingActionButton = {
            FABContent{
                navController.navigate(SummaryScreens.SurveyScreen.name)
            }
        }
            ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(navController = navController, viewModel)
        }
    }
}

@Composable
fun HomeContent(navController: NavController,
                viewModel: HomeScreenViewModel
){
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty()) email.split("@")?.get(0) else "N/A"
    val currentUser = FirebaseAuth.getInstance().currentUser

    var listOfUsers = emptyList<MUser>()

    if (!viewModel.userData.value.data.isNullOrEmpty()){
        listOfUsers = viewModel.userData.value?.data!!.toList()
    }

    Log.d("USER", "List of users: ${listOfUsers.toString()}")

    var listOfCompetence = emptyList<MOutcome>()
//
//    if (!viewModel.outcomeData.value.data.isNullOrEmpty()){
//        listOfCompetence = viewModel.outcomeData.value?.data!!.toList().filter { mOutcome ->
//            mOutcome.userId == currentUser?.uid.toString()
//        }
//    }

    if (!viewModel.outcomeData.value.data.isNullOrEmpty()){
        listOfCompetence = viewModel.outcomeData.value?.data!!.toList()

    }



    Column(
        Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Welcome $currentUserName")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {

                Icon(

                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(SummaryScreens.UserSettingsScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colors.secondaryVariant
                )
                Text(
                    text = currentUserName!!,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Divider()
            }
        }

        TitleSection(label = "Twoje Ocena")

        Text(text = "80", style = MaterialTheme.typography.h1, color = MaterialTheme.colors.primaryVariant, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

        TitleSection(label = "Twoja Kompetencje")
        CompetenceList(listOfCompetence = listOfCompetence, navController = navController)
        TitleSection(label = "Użytkownicy")
        UserList(listOfUsers = listOfUsers, navController = navController)
    }
}

@Composable
fun UserList(
    listOfUsers: List<MUser>,
    navController: NavController
) {
    UserScrollableComponent(listOfUsers){
//        Log.d("USER", "Value of it: $it") // todo: dowiedziec się czemu to gówno daje pusty string
        navController.navigate(SummaryScreens.UserDetailScreen.name + "/${it}")
    }
}

@Composable
fun CompetenceList(
    listOfCompetence: List<MOutcome>,
    navController: NavController
){
    CompetenceScrollableComponent(listOfCompetence){
//        Log.d("USER", "Value of competence: $it")
        navController.navigate(SummaryScreens.CompetenceDetailScreen.name + "/${it}")
    }
}

@Composable
fun CompetenceScrollableComponent(listOfCompetence: List<MOutcome>, onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(160.dp)
        .horizontalScroll(scrollState)){

        for (competence in listOfCompetence){
            CompetenceCard(competence = competence){
                onCardPressed(it)
            }
        }
    }
}
@Composable
fun UserScrollableComponent(listOfUsers: List<MUser>, onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(240.dp)
        .horizontalScroll(scrollState)){

        for (user in listOfUsers){
            UserCard(user = user){
                onCardPressed(it)
            }
        }
    }
}
