package com.example.readingapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readingapp.components.FilledButtonExample
import com.example.readingapp.ui.theme.ReadingAppTheme
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import kotlin.jvm.java
import androidx.compose.ui.unit.sp

data class UserRegistration(
    val login: String,
    val email: String,
    val password_hash: String
)

data class RegistrationResponse(
    val message: String,
    val user_id: Int? = null,
    val error: String? = null
)

interface APIservice{
    @POST("api/register")
    suspend fun registerUser(
        @Body userData: UserRegistration
    ): Response<RegistrationResponse>
}

object APIclient{
    const val BASE_URL = "http://192.168.0.2:8000/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: APIservice = retrofit.create(APIservice::class.java)
}

@Composable
fun RegistrationScreen(
){
    var login by remember { mutableStateOf("") }
    var password_hash by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var showAuthScreen by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var passwordError by remember { mutableStateOf<String?>(null) }
    var loginError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }

    if(showAuthScreen){
        AuthorizationScreen()
    }else{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(top = 230.dp).padding(horizontal = 32.dp)
            ){
                Text(
                    text="Регистрация",
                    fontFamily = interSemiBold,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 40.dp),)
                OutlinedTextField(
                    value = login,
                    onValueChange = { login = it
                        loginError = null},
                    label = { Text("Введите логин") },
                    shape = RoundedCornerShape(15.dp),
                    isError = loginError != null,
                    supportingText = {
                        if (loginError != null) {
                            Text(
                                text = loginError!!,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 12.dp.value.sp
                            )
                        }
                    }

                )
                OutlinedTextField(
                    value = password_hash,
                    onValueChange = { password_hash = it
                        passwordError = validatePassword(it)},
                    label = { Text("Введите пароль") },
                    shape = RoundedCornerShape(15.dp),
                    isError = passwordError != null,
                    supportingText = {
                        if (passwordError != null) {
                            Text(
                                text = passwordError!!,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 12.dp.value.sp
                            )
                        } else if (password_hash.isNotEmpty() && password_hash.length < 8) {
                            Text(
                                text = "Требуется минимум 8 символов",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 10.dp.value.sp
                            )
                        }
                    }
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it
                        emailError = validateEmail(it)},
                    label = { Text("Введите почту") },
                    shape = RoundedCornerShape(15.dp),
                    supportingText = {
                        if (emailError != null) {
                            Text(
                                text = emailError!!,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 12.dp.value.sp
                            )
                        }
                    }
                )
                FilledButtonExample(
                    onClick = {
                        val currentPasswordError = validatePassword(password_hash)
                        val currentLoginError = validateLogin(login)
                        val currentEmailError = validateEmail(email)

                        passwordError = currentPasswordError
                        loginError = currentLoginError
                        emailError = currentEmailError

                        if (currentPasswordError != null ||
                            currentLoginError != null ||
                            currentEmailError != null) {
                            return@FilledButtonExample
                        }

                        coroutineScope.launch {
                            val userData = UserRegistration(login, email, password_hash)
                            val response = APIclient.api.registerUser(userData)
                            if(response.isSuccessful){
                                val result = response.body()
                                showAuthScreen = true
                            }
                        }
                    },
                    text = "Продолжить",
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

private fun validatePassword(password: String): String? {
    return when {
        password.isBlank() -> "Пароль не может быть пустым"
        password.length < 8 -> "Пароль должен содержать минимум 8 символов"
        else -> null
    }
}

private fun validateLogin(login: String): String? {
    return when {
        login.isBlank() -> "Логин не может быть пустым"
        login.length < 3 -> "Логин должен содержать минимум 3 символа"
        login.length > 20 -> "Логин не должен превышать 20 символов"
        !login.matches(Regex("^[a-zA-Z0-9_]+$")) -> "Логин может содержать только буквы, цифры и нижнее подчеркивание"
        else -> null
    }
}

private fun validateEmail(email: String): String? {
    return when {
        email.isBlank() -> "Email не может быть пустым"
        !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Введите корректный email адрес"
        else -> null
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    ReadingAppTheme(dynamicColor = false) {
        RegistrationScreen()
    }
}