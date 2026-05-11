package com.example.readingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readingapp.ui.theme.ReadingAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showSplash by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(2000)
                showSplash = false
            }

            ReadingAppTheme(dynamicColor = false) {
                if (showSplash) {
                    SplashScreen()
                } else {
                    OpeningScreen2()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier, containerColor = MaterialTheme.colorScheme.surface
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("home") },
                    icon = { Icon(painter = painterResource(R.drawable.book), contentDescription = "иконка главной странинцы")  },
                    label = { Text("Главная") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {navController.navigate("search")},
                    icon = {Icon(painter = painterResource(R.drawable.search), contentDescription = "иконка поиска страницы")},
                    label = {Text("Поиск")}
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("read") },
                    icon = { Icon(painter = painterResource(R.drawable.read), contentDescription = "иконка страницы читалки") },
                    label = { Text("Читать") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("bookCollection") },
                    icon = { Icon(painter = painterResource(R.drawable.bookcollection), contentDescription = "иконка страницы с избранным") },
                    label = { Text("Избранное") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("account") },
                    icon = { Icon(painter = painterResource(R.drawable.account), contentDescription = "иконка страницы с профилем") },
                    label = { Text("Профиль") }
                )

            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { MainScreen() }
            composable("account") { Account() }
            composable("bookCollection") { BookCollection() }
            composable("search"){SearchScreen()}
            composable("read") {ReadingScreen()  }
        }
    }
}