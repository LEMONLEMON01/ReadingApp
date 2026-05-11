package com.example.readingapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readingapp.components.FilledButtonExample
import com.example.readingapp.ui.theme.ReadingAppTheme
@Composable
fun OpeningScreen2(){
    var showMainScreen by remember { mutableStateOf(false) }
    var showAuthScreen by remember { mutableStateOf(false) }

    if(showMainScreen){
        AppNavigation()
    }else if(showAuthScreen){
        AuthorizationScreen()
    }
    else{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 250.dp).padding(horizontal = 32.dp)
            ) {
                Text(
                    text="Таптыыр суруйааччыларгыт айымньыларын манна аа5ын.",
                    fontFamily = interSemiBold,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 50.dp),
                )

                FilledButtonExample(
                    onClick = { showAuthScreen = true },
                    text = "Войти или зарегистрироваться",
                    modifier = Modifier.padding(15.dp).padding(top = 200.dp).fillMaxWidth()
                )

                FilledButtonExample(
                    onClick = { showMainScreen = true },
                    text = "Позже",
                    modifier = Modifier.padding(15.dp).fillMaxWidth(),

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OpeningScreen2ScreenPreview() {
    ReadingAppTheme(dynamicColor = false) {
        OpeningScreen2()
    }
}