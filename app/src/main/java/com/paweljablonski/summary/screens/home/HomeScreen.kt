package com.paweljablonski.summary.screens.home
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


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
            //home content
        }
    }



}

@Composable
fun SummaryAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
){
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if(showProfile){
                    Icon(
                        imageVector = Icons.Default.Favorite, //Todo: change for app logo
                        contentDescription = "Logo Icon",
                        modifier = Modifier.clip(RoundedCornerShape(12.dp))
                            .scale(0.7f)
                    )
                }
                Text(
                    text = title,
                    color = Color.Red.copy(alpha = 0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
            }
        },
        actions = {},
        backgroundColor = Color.Transparent,
        elevation = 0.dp)
}




@Composable
fun FABContent(onTap: () -> Unit){
    FloatingActionButton(
        onClick = { onTap() },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant,
        ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add survey",
            tint = MaterialTheme.colors.onSecondary
        )
    }
}