package com.paweljablonski.summary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.paweljablonski.summary.screens.LoginScreen
//import com.paweljablonski.summary.screens.LoginScreen
import com.paweljablonski.summary.screens.SplashScreen
import com.paweljablonski.summary.screens.competence.CompetenceScreen
import com.paweljablonski.summary.screens.details.competence_detail.CompetenceDetailScreen
import com.paweljablonski.summary.screens.details.user.UserDetailScreen
import com.paweljablonski.summary.screens.home.Home
import com.paweljablonski.summary.screens.home.HomeScreenViewModel
import com.paweljablonski.summary.screens.survey.SurveyScreen
import com.paweljablonski.summary.screens.user_settings.UserSettingsScreen


@ExperimentalComposeUiApi
@Composable
fun SummaryNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SummaryScreens.SplashScreen.name){

        composable(SummaryScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(SummaryScreens.LoginScreen.name){
            LoginScreen(navController = navController)
        }
        composable(SummaryScreens.UserSettingsScreen.name){
            UserSettingsScreen(navController = navController)
        }
        
        composable(SummaryScreens.HomeScreen.name){
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            Home(navController = navController, homeViewModel)
        }


        val userDetailName = SummaryScreens.UserDetailScreen.name

        composable("$userDetailName/{userId}", arguments = listOf(
            navArgument("userId"){
                type = NavType.StringType
            }
        )){ backStackEntry ->
            backStackEntry.arguments?.getString("userId").let{
                UserDetailScreen(navController = navController, userId = it.toString())
            }
        }


        val competenceDetailName = SummaryScreens.CompetenceDetailScreen.name

        composable("$competenceDetailName/{competenceId}", arguments = listOf(navArgument("competenceId"){
            type = NavType.StringType
        })){    backStackEntry ->
            backStackEntry.arguments?.getString("competenceId").let {
                CompetenceDetailScreen(navController = navController, competenceId = it.toString())
            }
        }

        composable(SummaryScreens.CompetenceScreen.name){
            CompetenceScreen(navController = navController)
        }

        composable(SummaryScreens.SurveyScreen.name){
            SurveyScreen(navController = navController)
        }

    }
}