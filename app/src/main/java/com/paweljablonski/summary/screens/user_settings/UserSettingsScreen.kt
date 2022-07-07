package com.paweljablonski.summary.screens.user_settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.paweljablonski.summary.R


@Composable
fun UserSettingsScreen(navController: NavController){
    Scaffold(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        Column {

            UserLogo()
            ChangeName()
            ChangePassword()
            DeleteUser()
            }
    }
}


@Composable
fun UserLogo(){
    Column(modifier = Modifier.height(200.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            CreateImageProfile()
            Divider()
            Spacer(modifier = Modifier.padding(10.dp))

        }
}
@Composable
fun ChangeName(){
    Text("Zmiana nazwy użytkownika - editext i button")
    Row(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()){
        OutlinedText("Nazwa")
        Button(onClick = { /*TODO*/ }, modifier = Modifier.width(48.dp).height(48.dp)) {
        }
    }
}
@Composable
fun ChangePassword(){
    Text("Zmiana hasła - edit text i button")
    Row(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()){
        OutlinedText("Hasło")
        Button(onClick = { /*TODO*/ }, modifier = Modifier.width(48.dp).height(48.dp)) {
        }
    }
}
@Composable
fun DeleteUser(){
    Text("Usunięcie konta - Button", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    Button(onClick = { /*TODO*/ }, modifier = Modifier.width(148.dp).height(48.dp)) {
    }
}
@Composable
fun StyledTextField() {
    var value by remember { mutableStateOf("Hello\nWorld\nInvisible") }

    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Enter text") },
        maxLines = 2,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(20.dp)
    )
}
@Composable
fun OutlinedText(label: String) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) }
    )
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(modifier = modifier
        .size(150.dp)
        .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)) {

        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop)

    }

    Button(onClick = { /*TODO*/ }) {
        Text(text = "Zmień awatar")
    }
}
