package com.paweljablonski.summary.navigation

enum class SummaryScreens {
    SplashScreen,
    HomeScreen,
    SearchScreen,
    StatsScreen,
    UserSettingsScreen,
    LoginScreen,
    UserDetailScreen,
    CompetenceDetailScreen,
    CompetenceScreen,
    SurveyScreen
    ;

    companion object {
        fun fromRoute(route: String):SummaryScreens
        = when(route?.substringBefore("/")){
            SplashScreen.name -> SplashScreen
            HomeScreen.name -> HomeScreen
            SearchScreen.name -> SearchScreen
            StatsScreen.name -> StatsScreen
            LoginScreen.name -> LoginScreen
            UserSettingsScreen.name -> UserSettingsScreen
            UserDetailScreen.name -> UserDetailScreen
            CompetenceDetailScreen.name -> CompetenceDetailScreen
            CompetenceScreen.name -> CompetenceScreen
            SurveyScreen.name -> SurveyScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }

}