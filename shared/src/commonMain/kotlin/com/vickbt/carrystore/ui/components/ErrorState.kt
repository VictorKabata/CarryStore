package com.vickbt.carrystore.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorState(
    modifier: Modifier,
    errorIcon: ImageVector = Icons.Rounded.Error,
    errorMessage: String,
    actionMessage: String? = null,
    action: () -> Unit = {}
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(150.dp),
            imageVector = errorIcon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )

        Text(text = errorMessage, fontSize = 16.sp, textAlign = TextAlign.Center)

        actionMessage?.let {
            Button(
                modifier = Modifier.fillMaxWidth(.60f).padding(vertical = 6.dp),
                onClick = { action() },
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = it,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

    }

}