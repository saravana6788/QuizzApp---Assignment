package com.cricut.androidassessment.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuestionTextView(
    questionText : String,
) {
    Column(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = questionText,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            lineHeight = 25.sp,
            fontSize = 20.sp
            )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionTextViewPreview(){
    QuestionTextView("This is the question on the android and Kotlin. Be prepared buddy")
}