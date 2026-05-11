package com.example.readingapp

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readingapp.components.FilledButtonExample
import com.example.readingapp.ui.theme.ReadingAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.json.JSONObject


val interSemiBold = FontFamily(Font(R.font.intersemibold))

@Composable
fun AuthorizationScreen(
){
    var showRegScreen by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showMainScreen by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val client = remember { OkHttpClient() }

    if(showRegScreen){
        RegistrationScreen()
    }else if(showMainScreen){
        AppNavigation()
    } else{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 250.dp).padding(horizontal = 32.dp)
            ){
                Text(
                    text="Войти",
                    fontFamily = interSemiBold,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 10.dp),)
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    label = { Text("Введите логин или почту") },
                    shape = RoundedCornerShape(15.dp),
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Введите пароль") },
                    shape = RoundedCornerShape(15.dp),
                )
                FilledButtonExample(
                    onClick = {
                        if (input.isNotBlank() && password.isNotBlank()) {
                            scope.launch {
                                val result = login(
                                    client = client,
                                    login = input,
                                    password = password
                                )

                                if(result.isSuccess){
                                    showMainScreen = true
                                }
                            }
                        }

                    },
                    text = "Продолжить",
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text="или",
                    fontFamily = interSemiBold,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 10.dp),
                    fontSize = 15.sp
                )
                FilledButtonExample(
                    onClick = {showRegScreen = true},
                    text = "Зарегистрироваться",
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

suspend fun login(
    client: OkHttpClient,
    login: String,
    password: String
): Result<String> {
    return withContext(Dispatchers.IO) {
        try {
            val jsonObject = JSONObject()
            jsonObject.put("login", login)
            jsonObject.put("password_hash", password)

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonObject.toString().toRequestBody(mediaType)

            val request = Request.Builder()
                .url("http://192.168.0.2:8000/api/login")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()

            if (response.isSuccessful) {
                val json = JSONObject(responseBody ?: "{}")
                val message = json.optString("message", "Успешный вход")
                Result.success(message)
            } else {
                val errorJson = JSONObject(responseBody ?: "{}")
                val errorDetail = errorJson.optString("detail", "Ошибка авторизации")
                Result.failure(Exception(errorDetail))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Ошибка сети: ${e.message}"))
        } catch (e: Exception) {
            Result.failure(Exception("Ошибка: ${e.message}"))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorizationScreenPreview() {
    ReadingAppTheme(dynamicColor = false) {
        AuthorizationScreen()
    }
}