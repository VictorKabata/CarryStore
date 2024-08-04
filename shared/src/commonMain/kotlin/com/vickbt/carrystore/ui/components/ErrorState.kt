package com.vickbt.carrystore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorState(
    modifier: Modifier,
    errorIcon: ImageVector = Icons.Rounded.Person,
    errorMessage: String,
    actionMessage: String? = null,
    action: () -> Unit = {}
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(250.dp),
            imageVector = errorIcon,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )

        Text(text = errorMessage, fontSize = 16.sp, textAlign = TextAlign.Center)

        actionMessage?.let {
            Button(
                modifier = Modifier,
                onClick = { action() },
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = it, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            }
        }

    }

}