package com.paweljablonski.summary.navigation

enum class SummaryScreens {
    SplashScreen,
    HomeScreen,
    SearchScreen,
    StatsScreen,
    UserScreen,
    LoginScreen;

    companion object {
        fun fromRoute(route: String):SummaryScreens
        = when(route?.substringBefore("/")){
            SplashScreen.name -> SplashScreen
            HomeScreen.name -> HomeScreen
            SearchScreen.name -> SearchScreen
            StatsScreen.name -> StatsScreen
            LoginScreen.name -> LoginScreen
            UserScreen.name -> UserScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }

}