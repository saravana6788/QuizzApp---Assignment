package com.cricut.androidassessment.presentation.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cricut.androidassessment.R
import com.cricut.androidassessment.presentation.Screen.Screen
import com.cricut.androidassessment.presentation.components.QuestionNavButton
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

@Composable
fun IntroScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.ready_to_take_the_quiz),
            fontSize = 20.sp,
            color =  MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
            )
        Spacer(modifier = Modifier.height(16.dp))
        QuestionNavButton(
            title = stringResource(R.string.start_quiz),
            onClick = {
                navController.navigate(Screen.AssessmentScreen.route)
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun IntroScreenPreview(){
    AndroidAssessmentTheme {
        IntroScreen(navController = rememberNavController())
    }
}