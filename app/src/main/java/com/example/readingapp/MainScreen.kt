package com.example.readingapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readingapp.components.BookComponent
import com.example.readingapp.components.GenreRow
import com.example.readingapp.ui.theme.ReadingAppTheme

@Composable
fun MainScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.padding(start = 15.dp)) {

            Text(
                text="Главное",
                fontFamily = interSemiBold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 60.dp),
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items(5){
                    GenreRow()
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ReadingAppTheme(dynamicColor = false) {
        MainScreen()
    }
}