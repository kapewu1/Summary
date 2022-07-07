package com.paweljablonski.summary.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.rounded.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.paweljablonski.summary.R
import com.paweljablonski.summary.model.MCompetence
import com.paweljablonski.summary.model.MUser
import com.paweljablonski.summary.navigation.SummaryScreens


@Composable
fun SummaryLogo(modifier: Modifier = Modifier) {
    Text(text = "Summary",
        modifier = modifier.padding(bottom = 16.dp),
        style = MaterialTheme.typography.h3,
        color = Color.Red.copy(alpha = 0.5f))
}


@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction)


}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(value = valueState.value,
        onValueChange = { valueState.value = it},
        label = { Text(text = labelId)},
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction)
}


@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
) {

    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()
    OutlinedTextField(value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction),
        visualTransformation = visualTransformation,
        trailingIcon = {PasswordVisibility(passwordVisibility = passwordVisibility)},
        keyboardActions = onAction)

}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible}) {
        Icons.Default.Close
    }

}

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    label: String
){
    Surface(modifier = modifier.padding(start = 5.dp, top = 1.dp)) {
        Column {
            Text(text = label,
                fontSize = 19.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Left
            )
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
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .scale(0.7f)
                    )
                }
                Text(
                    text = title,
                    color = Color.Red.copy(alpha = 0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.width(150.dp))
            }
        },
        actions = {
            IconButton(onClick = {
                FirebaseAuth.getInstance().signOut().run {
                    navController.navigate(SummaryScreens.LoginScreen.name)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Logout"
                    //tint = Color.Green
                )
            }
        },
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


//@Preview
@Composable
fun CompetenceCard(competence: MCompetence = MCompetence("231sw", "Komunikacja", "Lorem ipsum", score = 65),
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
            .clickable { onPressDetails.invoke(competence.name)}
//            .fillMaxWidth().heightIn(180.dp)
            .widthIn(120.dp)
            .heightIn(120.dp)
    ) {
        Column(modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            verticalArrangement = Arrangement.Center
//            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.Start
//            Row(horizontalArrangement = Arrangement.Start
            ) {
                Text(text = competence.name,
                    modifier = Modifier
                        .padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,

                    )
                Spacer(modifier = Modifier.width(100.dp))

                Text(
                    text = competence.score.toString(),
                    style = MaterialTheme.typography.body2,
                    fontSize = 36.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


@Composable
fun UserCard(user: MUser,
             onPressDetails: (String) -> Unit = {}) {


    Card(modifier = Modifier
        .width(200.dp)
        .height(290.dp)
        .padding(12.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 4.dp) {
        Column(modifier =
        Modifier
            .height(200.dp)
            .padding(16.dp)
            .clickable { onPressDetails.invoke(user.displayName)},
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            CreateImageProfile()
            Divider()
            Text(text = user.displayName, style = MaterialTheme.typography.h5, color = MaterialTheme.colors.primaryVariant, textAlign = TextAlign.Center)
        }

    }
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
}




@Preview
@Composable
fun ScoreViewCard() {
    Card(modifier = Modifier
        .width(200.dp)
        .height(390.dp)
        .padding(12.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 4.dp) {
        Column(modifier = Modifier.height(200.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            CreateImageProfile()
            Divider()
            Text(text = "Twój wynik", style = MaterialTheme.typography.h5, color = MaterialTheme.colors.primaryVariant, textAlign = TextAlign.Center)
        }

    }

}

