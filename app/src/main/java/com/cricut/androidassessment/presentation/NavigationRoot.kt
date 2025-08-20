package com.cricut.androidassessment.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cricut.androidassessment.presentation.Screen.Screen
import com.cricut.androidassessment.presentation.assessment.AssessmentScreen
import com.cricut.androidassessment.presentation.intro.IntroScreen
import com.cricut.androidassessment.presentation.result.ResultScreen

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.IntroScreen.route){
        composable(
            route = Screen.IntroScreen.route
        ){
            IntroScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.AssessmentScreen.route
        ){
            AssessmentScreen(navController = navController)
        }

        composable(
            route = Screen.ResultScreen.route
        ){
            ResultScreen(navController = navController)
        }
    }
}