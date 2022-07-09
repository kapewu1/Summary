package com.paweljablonski.summary.screens.competence

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CompetenceScreen(navController: NavController){
    Text("Nazwa")
    StyledTextField(2)
    Text("Opis")
    StyledTextField(10)
    ButtonAddCompetence()

}

@Composable
fun ButtonAddCompetence() {
    Button(onClick = {

    }) {
        Text(text = "Dodaj kompetencję")
    }
}


@Composable
fun StyledTextField(maxLines: Int) {
    var value by remember { mutableStateOf("Hello\nWorld\nInvisible") }

    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Enter text") },
        maxLines = maxLines,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(20.dp)
    )
}