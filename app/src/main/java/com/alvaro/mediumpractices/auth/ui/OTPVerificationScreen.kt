package com.alvaro.mediumpractices.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alvaro.mediumpractices.auth.ui.common.HeaderAuth
import com.alvaro.mediumpractices.auth.ui.common.authTopBar
import com.alvaro.mediumpractices.ui.theme.Urbanist

@Composable
fun OtpVerificationScreen() {
    Scaffold(topBar = { authTopBar() }) {
        Column(Modifier.padding(it)) {
            HeaderOtpVerification()
            Spacer(Modifier.height(16.dp))
            FormOtpVerification()
        }
    }

}

@Composable
fun HeaderOtpVerification() {
    Column(Modifier.fillMaxWidth()) {
        HeaderAuth(title = "OTP Verification")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Enter the verification code we just sent on your email address.",
            modifier = Modifier.padding(horizontal = 12.dp),
            fontFamily = Urbanist,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xff8391A1)
        )
    }

}

@Composable
fun FormOtpVerification() {
    var otpValue by remember {
        mutableStateOf("")
    }

    BasicTextField(
        value = otpValue, onValueChange = {
            if (it.length <= 4) {
                otpValue = it
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),

                ) {

                repeat(4) { index ->
                    val char = when {
                        index >= otpValue.length -> ""
                        else -> otpValue[index].toString()
                    }

                    val isFocused = (otpValue.length - 1) >= index

                    Box(
                        modifier = Modifier
                            .size(width = 70.dp, height = 60.dp)
                            .align(Alignment.CenterVertically)
                            .border(
                                if (isFocused) 2.dp else 1.dp,
                                if (isFocused) Color(0xff35C2C1) else Color(0xffE8ECF4),
                                RoundedCornerShape(8.dp)
                            )
                            .background(if (isFocused) Color.White else Color(0xffF7F8F9)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char, fontFamily = Urbanist,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }

                }

            }

        }
    )

}


@Preview
@Composable
fun PreviewOtpVerificationScreen() {
    OtpVerificationScreen()
}