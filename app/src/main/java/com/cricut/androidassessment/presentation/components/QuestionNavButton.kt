package com.cricut.androidassessment.presentation.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuestionNavButton(
    title:String,
    modifier:Modifier = Modifier,
    onClick:()->Unit,
    isEnabled:Boolean = true
) {
    Button(
        modifier = modifier.width(160.dp),
        onClick = onClick,
        enabled = isEnabled,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.background,
            containerColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionNavButtonPreview(){
    QuestionNavButton(title = "Prev Question", onClick = {})
}