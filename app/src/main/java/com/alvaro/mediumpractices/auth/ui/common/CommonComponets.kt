package com.alvaro.mediumpractices.auth.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alvaro.mediumpractices.R
import com.alvaro.mediumpractices.auth.login.ui.LoginSocialButtons

import com.alvaro.mediumpractices.ui.theme.Urbanist


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthButtons(
    label: String,
    onClick: () -> Unit,
    containerColor: Color = Color.Black,
    textColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(8.dp),
    borderColor: Color = Color.Black,
    enabled: Boolean = true
) {

    val controller = LocalSoftwareKeyboardController.current
    Button(
        onClick = {
            controller?.hide()
            onClick()
        },
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
        //   .padding(horizontal = 18.dp),
        , shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
        ), border = if (enabled) BorderStroke(1.dp, borderColor) else null, enabled = enabled

    ) {
        Text(
            text = label,
            fontFamily = Urbanist,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
    }
}

@Composable
fun TextFieldLogin(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPasswordField: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    errorMessage: String = "",
    enabled: Boolean = true
) {
    var showPassword by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xffE8ECF4),
            unfocusedBorderColor = Color(0xffE8ECF4),
            unfocusedContainerColor = Color(0xffF7F8F9),
            focusedContainerColor = Color(0xffF7F8F9),
            errorContainerColor = Color(0xffF7F8F9)
        ),
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp, color = Color(0xff8391A1)
            )
        },

        trailingIcon = {
            if (isPasswordField) {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null, tint = Color(0xff6A707C)
                    )
                }
            }
        },

        visualTransformation = if (showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        isError = isError,
        supportingText = {

            if (isError) {
                Text(
                    text = errorMessage,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }, singleLine = true, enabled = enabled
    )
}


@Composable
fun authTopBar(navController: NavController) {

    Box(Modifier.fillMaxWidth()) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 32.dp)
                .border(
                    BorderStroke(1.dp, color = Color(0xffE8ECF4)),
                    shape = RoundedCornerShape(8.dp)
                )

        ) {
            Icon(
                imageVector = Icons.Filled.ChevronLeft,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }

}

@Composable
fun HeaderAuth(title: String) {
    Text(
        text = title,
        fontFamily = Urbanist,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 12.dp, end = 84.dp)
    )

}

@Composable
fun SocialAuth() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        LoginSocialButtons(icon = R.drawable.facebook_ic)
        LoginSocialButtons(icon = R.drawable.google_ic)
        LoginSocialButtons(icon = R.drawable.apple_ic)

    }
}

@Composable
fun FooterAuth(label: String, labelClickable: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontFamily = Urbanist,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
        TextButton(onClick = { onClick() }, contentPadding = PaddingValues(start = 2.dp)) {
            Text(
                text = labelClickable,
                fontFamily = Urbanist,
                fontSize = 15.sp,
                color = Color(0xff35C2C1),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun LoginDialog(
    title: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(text = title, fontFamily = Urbanist)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTextFieldLogin() {
    TextFieldLogin(
        "Hola",
        placeholder = "Email",
        onValueChange = {},
        errorMessage = "",
        enabled = true
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewAuthButtons() {
    AuthButtons(onClick = {}, label = "Login")

}