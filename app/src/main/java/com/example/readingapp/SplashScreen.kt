package com.example.readingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readingapp.ui.theme.ReadingAppTheme

val kazimirRegular = FontFamily(
    Font(R.font.kazimirtext)
)

val interSemibold = FontFamily(
    Font(R.font.intersemibold)
)
@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC6E4A2)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "Логотип",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(top = 150.dp),
            text = "АЙЫМНЬЫТ",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontFamily = interSemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReadingAppSplashPreview() {
    ReadingAppTheme(dynamicColor = false) {
        SplashScreen()
    }
}