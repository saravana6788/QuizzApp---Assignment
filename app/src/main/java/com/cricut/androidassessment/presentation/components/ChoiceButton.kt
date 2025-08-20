package com.cricut.androidassessment.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChoiceButton(
    title : String,
    modifier: Modifier =  Modifier,
    onClick: () ->Unit,
    isEnabled: Boolean = true,
    onSelected: Boolean = false
){
    OutlinedButton(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 20.dp),
        onClick = onClick,
        enabled = isEnabled,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(onSelected) MaterialTheme.colorScheme.onSurface else Color.Transparent,
            contentColor = if(onSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
            disabledContentColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.secondary
        )

    ) {
        Text(text = title,
            fontSize = 16.sp)
    }
}

@Preview
@Composable
fun ChoiceButtonPreview(){
    ChoiceButton("True",
        onClick = {},
        isEnabled = true)
}