package com.example.readingapp.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.*

@Composable
fun FilledButtonExample(onClick: () -> Unit, text: String = "Filled", modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(onClick = onClick, modifier = modifier, enabled = enabled ){
        Text(text)
    }
}