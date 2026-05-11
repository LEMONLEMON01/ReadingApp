package com.example.readingapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun BookComponent(onClick: () -> Unit, authorName: String = "Author name", bookName: String = "Book name", path: String = "", modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.size(150.dp, 250.dp)){
            if(path.isNotEmpty()){
                AsyncImage(
                    model = path,
                    contentDescription = "Book cover for $bookName",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }else{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                        contentDescription = "Placeholder",
                        modifier = Modifier.size(50.dp),
                        tint = Color.Gray
                    )
                }
            }
        }
        Text(bookName, fontWeight = FontWeight.Bold)
        Text(authorName)
        FilledButtonExample(onClick = onClick, text = "Читать", modifier = Modifier.fillMaxWidth())
    }
}