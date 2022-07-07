package com.paweljablonski.summary.screens.home
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.paweljablonski.summary.components.*
import com.paweljablonski.summary.model.MCompetence
import com.paweljablonski.summary.model.MUser
import com.paweljablonski.summary.navigation.SummaryScreens


@Composable
fun Home(navController: NavController) {
    Scaffold (
        topBar = {
            SummaryAppBar("Summary", navController = navController)
        },
        floatingActionButton = {
            FABContent{
            }
        }
            ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(navController = navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavController){
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty()) email.split("@")?.get(0) else "N/A"

    val listOfUsers = listOf(
        MUser("1", "dasdw2d21", "Karen", "sadas", bio = "I like trains", department = "HR"),
        MUser("2", "fasffas22", "Bob", "sadas", bio = "Democracy is an evil creature", department = "CEO"),
        MUser("3", "dasdd222h", "Jeremy", "sadas", bio = "010100111", department = "Development Team")
    )

    val listOfCompetence = listOf(
        MCompetence("1", "Komunikacja", "Lorem", 61),
        MCompetence("2", "Zaangażowanie", "Lorem", 100),
        MCompetence("3", "Determinacja", "Lorem", 76),
        MCompetence("4", "Programowanie", "Lorem", 66),
        MCompetence("5", "Analityczne Myślenie", "Lorem", 82),
        MCompetence("6", "Podejmowanie Decyzji", "Lorem", 87)
    )

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
                            navController.navigate(SummaryScreens.UserScreen.name)
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
        TitleSection(label = "Twoje Kompetencje")
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
        //Todo: on card clicked navigate to details
    }
}

@Composable
fun CompetenceList(
    listOfCompetence: List<MCompetence>,
    navController: NavController
){
    CompetenceScrollableComponent(listOfCompetence){
        //Todo: on card clicked navigate to details
    }
}

@Composable
fun CompetenceScrollableComponent(listOfCompetence: List<MCompetence>, onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(180.dp)
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
        .heightIn(280.dp)
        .horizontalScroll(scrollState)){

        for (user in listOfUsers){
            UserCard(user = user){
                onCardPressed(it)
            }
        }
    }
}
