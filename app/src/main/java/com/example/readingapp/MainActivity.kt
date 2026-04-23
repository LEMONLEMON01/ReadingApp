package com.example.readingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readingapp.ui.theme.ReadingAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var splash by remember{mutableStateOf(true)}

            LaunchedEffect(Unit) {
                delay(2000)
                splash = false
            }

            if(splash){
                ReadingAppSplash()
            }else{
                AuthorizationScreen()
            }
            /*AuthorizationScreen()*/
        }
    }
}

val kazimirRegular = FontFamily(
    Font(R.font.kazimirtext)
)

val interSemibold = FontFamily(
    Font(R.font.intersemibold)
)

@Composable
fun ReadingAppSplash() {
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
        ReadingAppSplash()
    }
}
