package com.example.readingapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readingapp.components.FavoriteBook
import com.example.readingapp.ui.theme.ReadingAppTheme

@Composable
fun BookCollection(
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text(
            text="Избранное",
            fontFamily = interSemiBold,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 50.dp).padding(start = 20.dp),)
            LazyColumn() {
                items(20){
                    FavoriteBook(onClick = {})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookCollectionPreview() {
    ReadingAppTheme(dynamicColor = false) {
        BookCollection()
    }
}