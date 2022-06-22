package com.paweljablonski.summary.screens.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.paweljablonski.summary.components.FABContent
import com.paweljablonski.summary.components.SummaryAppBar
import com.paweljablonski.summary.components.TitleSection
import com.paweljablonski.summary.model.MCompetence
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
fun ListCard(competence: MCompetence = MCompetence("231sw", "Komunikacja", "Lorem ipsum", score = 65),
            onPressDetails: (String) -> Unit = {}) {

        val context = LocalContext.current
        val resources = context.resources
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels/ displayMetrics.density
        val spacing = 10.dp
        Card(
            shape = RoundedCornerShape(29.dp),
            backgroundColor = Color.White,
            elevation = 6.dp,
            modifier = Modifier
                .padding(16.dp)
                .height(82.dp)
//                .width(202.dp)
                .clickable { onPressDetails.invoke(competence.name.toString()) }

        ) {
            Column(modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
//            Column(modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
                horizontalAlignment = Alignment.Start
            ) {
                Row(horizontalArrangement = Arrangement.Center) {
                    Image(painter = rememberImagePainter(data = ""), contentDescription= "competence image",
                        modifier = Modifier
                            .height(140.dp)
                            .width(100.dp)
                            .padding(4.dp)
                        )
                    Spacer(modifier = Modifier.width(25.dp))
                    Text(
                        text = competence.score.toString(),
                        style = MaterialTheme.typography.body2,
                        fontSize = 36.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Text(text = competence.name,
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 24.sp
                    )
            }
        }
}


@Composable
fun HomeContent(navController: NavController){
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty()) email.split("@")?.get(0) else "N/A"

    Column(
        Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Employee to evaluate")
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
        ListCard()
    }
}

