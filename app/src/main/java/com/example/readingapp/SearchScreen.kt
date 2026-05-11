package com.example.readingapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readingapp.components.SearchBar
import com.example.readingapp.ui.theme.ReadingAppTheme

val sampleBooks = listOf(
    "Гарри Поттер и философский камень",
    "Гарри Поттер и Тайная комната",
    "Властелин колец: Братство кольца",
    "Властелин колец: Две крепости",
    "1984",
    "Скотный двор",
    "Гордость и предубеждение",
    "Анна Каренина",
    "Преступление и наказание",
    "Мастер и Маргарита"
)
@Composable
fun SearchScreen(
){
    val textFieldState = remember { TextFieldState() }
    var searchResults by remember { mutableStateOf(listOf<String>()) }

    fun performSearch(query: String) {
        if (query.isBlank()) {
            searchResults = emptyList()
        } else {
            searchResults = sampleBooks.filter { book ->
                book.contains(query, ignoreCase = true)
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column() {
            Text(
                text="Поиск",
                fontFamily = interSemiBold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 50.dp).padding(start = 20.dp),)
            SearchBar(textFieldState = textFieldState,
                onSearch = {query -> performSearch(query)},
                searchResults = searchResults)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    ReadingAppTheme(dynamicColor = false) {
        SearchScreen()
    }
}