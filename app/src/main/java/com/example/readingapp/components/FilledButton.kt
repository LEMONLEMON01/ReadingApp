package com.example.readingapp.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FilledButtonExample(onClick: () -> Unit, text: String = "Filled", modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(onClick = onClick, modifier = modifier, enabled = enabled, colors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFFC6E4A2),
        contentColor = Color.White,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.LightGray
    )){
        Text(text)
    }
}