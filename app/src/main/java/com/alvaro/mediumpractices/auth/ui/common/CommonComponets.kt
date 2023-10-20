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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alvaro.mediumpractices.R
import com.alvaro.mediumpractices.auth.ui.LoginSocialButtons
import com.alvaro.mediumpractices.ui.theme.Urbanist


@Composable
fun AuthButtons(
    label: String,
    onClick: () -> Unit,
    containerColor: Color = Color.Black,
    textColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(8.dp),
    borderColor: Color = Color.Black
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
        //   .padding(horizontal = 18.dp),
        , shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ), border = BorderStroke(1.dp, borderColor)

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
    isPasswordField: Boolean = false
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
            focusedContainerColor = Color(0xffF7F8F9)
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

        visualTransformation = if (showPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}


@Composable
fun authTopBar() {

    Box(Modifier.fillMaxWidth()) {
        IconButton(
            onClick = { /*TODO*/ },
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
fun FooterAuth(label:String, labelClickable:String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label ,
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

@Preview(showBackground = true)
@Composable
fun PreviewTextFieldLogin() {
    TextFieldLogin("Hola", placeholder = "Email", onValueChange = {})

}

@Preview(showBackground = true)
@Composable
fun PreviewAuthButtons() {
    AuthButtons(onClick = {}, label = "Login")

}