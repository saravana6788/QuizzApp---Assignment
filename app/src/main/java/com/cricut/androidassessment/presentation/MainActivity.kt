package com.cricut.androidassessment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cricut.androidassessment.presentation.Screen.Screen
import com.cricut.androidassessment.presentation.assessment.AssessmentScreen
import com.cricut.androidassessment.presentation.intro.IntroScreen
import com.cricut.androidassessment.presentation.result.ResultScreen
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidAssessmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                    .safeDrawingPadding(),
                    containerColor = MaterialTheme.colorScheme.surface) { innerPadding ->
                    val navController = rememberNavController()
                    NavigationRoot(navController)
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
            }
        }
    }
}
