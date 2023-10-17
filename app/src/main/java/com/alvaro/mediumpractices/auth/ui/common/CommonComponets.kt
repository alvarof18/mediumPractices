package com.alvaro.mediumpractices.auth.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alvaro.mediumpractices.ui.theme.Urbanist


@Composable
fun AuthButtons(
    modifier: Modifier = Modifier,
    label: String = "",
    onClick: () -> Unit,
    containerColor: Color = Color.Black,
    textColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(8.dp),
    borderColor: Color = Color.Black
) {
    Button(
        onClick = { onClick() },
        modifier = modifier

            .height(56.dp)
            .padding(horizontal = 18.dp),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ), border = BorderStroke(1.dp, borderColor)

    ) {
        Text(text = label, fontFamily = Urbanist, color = textColor)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAuthButtons() {
    AuthButtons(onClick = {})

}