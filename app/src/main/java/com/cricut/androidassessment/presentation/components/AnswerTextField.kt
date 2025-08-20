package com.cricut.androidassessment.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

@Composable
fun AnswerTextField(
    modifier: Modifier = Modifier,
    value:String,
    onAnswerChanged:(String) -> Unit,
    placeholder: String
){
        OutlinedTextField(
            modifier = modifier.fillMaxWidth().height(200.dp).padding(
                horizontal = 16.dp
            ),
            value = value,
            onValueChange = onAnswerChanged,
            enabled = true,
            placeholder = { Text(placeholder,
                color = MaterialTheme.colorScheme.onBackground) } ,
            shape = RoundedCornerShape(5.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground
            ),

        )
}




@Preview(showBackground = true)
@Composable
fun AnswerTextFieldPreview(){
    AndroidAssessmentTheme {
        AnswerTextField(
            value = "",
            onAnswerChanged = {},
            placeholder = "Hellow"
        )
    }
}