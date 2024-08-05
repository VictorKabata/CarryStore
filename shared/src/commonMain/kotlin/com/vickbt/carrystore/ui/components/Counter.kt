@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.vickbt.carrystore.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    count: Int = 1,
    maxCount: Int,
    countButtonSize: Dp = 48.dp,
    countTextSize: TextUnit = 24.sp,
    onIncrement: (Int) -> Unit,
    onDecrement: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            modifier = Modifier
                .size(countButtonSize)
                .combinedClickable(
                    onClick = { onDecrement(count) },
                    onLongClick = { onDecrement(count) }
                )
                .clip(CircleShape),
            enabled = count > 1,
            colors = IconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            onClick = { onDecrement(count) }) {
            Icon(
                imageVector = Icons.Rounded.Remove, contentDescription = null
            )
        }

        Text(
            modifier = Modifier,
            text = count.toString(),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            fontSize = countTextSize
        )

        IconButton(
            modifier = Modifier
                .size(countButtonSize)
                .combinedClickable(
                    onClick = { onIncrement(count) },
                    onLongClick = { onIncrement(count) }
                )
                .clip(CircleShape),
            enabled = count < maxCount,
            colors = IconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            onClick = { onIncrement(count) }) {
            Icon(
                imageVector = Icons.Rounded.Add, contentDescription = null
            )
        }
    }
}