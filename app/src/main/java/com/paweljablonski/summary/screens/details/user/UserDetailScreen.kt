package com.paweljablonski.summary.screens.details.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MUser
import com.paweljablonski.summary.screens.home.HomeScreenViewModel


@Composable
fun UserDetailScreen(
    navController: NavController,
    userId: String,
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    Scaffold(
    ) {


        val userInfo = produceState<DataOrException<List<MUser>,
                Boolean,
                Exception>>(initialValue = DataOrException(data = emptyList(),
            true, Exception(""))
        ){

            value = viewModel.userData.value
        }.value

        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(3.dp)) {
            Column(modifier = Modifier.padding(top = 3.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (userInfo.loading == true){
                    LinearProgressIndicator()
                    userInfo.loading = false
                } else{

                    var userQuery = emptyList<MUser>()
                    if (!viewModel.competenceData.value.data.isNullOrEmpty()){
                        userQuery = viewModel.userData.value?.data!!.toList()
                            .filter { mUser ->
                                mUser.userId!!.trim() == userId.trim()
                            }
                    }

                    if (userQuery.isNotEmpty()){
                        val user = userQuery.first()
                        Text(text = user.displayName)
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(text = user.displayName)
                    }
                }
            }
        }
    }
}

