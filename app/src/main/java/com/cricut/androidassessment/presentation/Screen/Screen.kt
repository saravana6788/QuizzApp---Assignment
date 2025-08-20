package com.cricut.androidassessment.presentation.Screen

sealed class Screen(val route:String) {
    data object IntroScreen:Screen("intro_screen")
    data object AssessmentScreen:Screen("assessment_screen")
    data object ResultScreen:Screen("result_screen")
}