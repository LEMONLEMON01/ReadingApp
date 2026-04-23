package com.example.readingapp

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readingapp.ui.theme.ReadingAppTheme

val interSemiBold = FontFamily(Font(R.font.intersemibold))
val interRegular = FontFamily(Font(R.font.interregular))

@Composable
fun AuthorizationScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text="Авторизация",
            fontFamily = interSemiBold,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 50.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorizationScreenPreview() {
    ReadingAppTheme(dynamicColor = false) {
        AuthorizationScreen()
    }
}