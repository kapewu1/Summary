package com.paweljablonski.summary.screens.competence

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.paweljablonski.summary.components.InputField
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MCompetence
import com.paweljablonski.summary.model.MOutcome
import com.paweljablonski.summary.model.MUser
import com.paweljablonski.summary.navigation.SummaryScreens

@Composable
fun CompetenceScreen(navController: NavController){

    StyledTextField(2)

    StyledTextField(10)
    ButtonAddCompetence()
    CompetenceContent(navController)
}

@Composable
fun CompetenceContent(navController: NavController){
    Scaffold {
        androidx.compose.material.Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        ) {
            Column(
                modifier = Modifier.padding(top = 3.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val name = rememberSaveable { mutableStateOf("")}
                val description = rememberSaveable { mutableStateOf("")}
                val valid = remember(name.value, description.value){
                    name.value.trim().isNotEmpty() && description.value.isNotEmpty()
                }

                Text("Dodaj kompetencje")
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp))
                Text("Nazwa")
                InputField(valueState = name,
                    labelId = "Enter Competence Name",
                    enabled = true)

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp))
                Text("Opis")
                InputField(
                    modifier = Modifier
                        .height(140.dp)
                        .padding(3.dp)
                        .background(Color.White, CircleShape)
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    valueState = description,
                    labelId = "Enter Competence Name",
                    enabled = true
                )

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                    horizontalArrangement = Arrangement.Center

                ){
                    Button(onClick = {
                        if (valid){
                            val competence = MCompetence(
                                name = name.value,
                                description = description.value,
                                competenceId = "test",
                                id = null
                            ).toMap()
                            val collection = FirebaseFirestore.getInstance().collection("competences")

                            collection
                                .add(competence).addOnSuccessListener { documentReference ->
                                    collection.document(documentReference.id).update("competenceId", documentReference.id)
                                    navController.navigate(SummaryScreens.HomeScreen.name)
                                }
                        } else{
                            Log.d("TAG","Competence Screen: One of the spacer are blank")
                        }
                    }) {
                        Text("Dodaj")
                    }
                    Spacer(modifier = Modifier.width(12.dp))

                    Button(onClick = {
                        navController.navigate(SummaryScreens.HomeScreen.name)
                    }) {
                        Text("Anuluj")
                    }
                }
            }
        }
    }
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
    var value by remember { mutableStateOf("") }


}