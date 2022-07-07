package com.paweljablonski.summary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paweljablonski.summary.screens.LoginScreen
//import com.paweljablonski.summary.screens.LoginScreen
import com.paweljablonski.summary.screens.SplashScreen
import com.paweljablonski.summary.screens.details.competence.CompetenceScreen
import com.paweljablonski.summary.screens.details.user.UserScreen
import com.paweljablonski.summary.screens.home.Home
import com.paweljablonski.summary.screens.user_settings.UserSettingsScreen

//import com.paweljablonski.summary.screens.home.Home


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
            Home(navController = navController)
        }

        composable(SummaryScreens.UserScreen.name){
            UserScreen(navController = navController)
        }


        composable(SummaryScreens.CompetenceScreen.name){
            CompetenceScreen(navController = navController)
        }


//        composable(SummaryScreens.HomeScreen.name){
//            Home(navController = navController)
//        }
//        composable(SummaryScreens.LoginScreen.name){
//            LoginScreen(navController = navController)
//        }
    }
}