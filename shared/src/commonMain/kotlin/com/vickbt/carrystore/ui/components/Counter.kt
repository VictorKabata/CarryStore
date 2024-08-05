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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    countButtonSize: Dp = Dp.Unspecified,
    countTextSize: TextUnit = 24.sp,
    onIncrement: (Int) -> Unit,
    onDecrement: (Int) -> Unit
) {

    val some = 16.sp
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        FloatingActionButton(
            modifier = Modifier.size(countButtonSize).combinedClickable(
                enabled = count >= 1,
                onClick = { onDecrement(count) },
                onLongClick = { onDecrement(count) }
            ),
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            onClick = { onDecrement(count) },
        ) {
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

        FloatingActionButton(
            modifier = Modifier.size(countButtonSize).combinedClickable(
                enabled = count < maxCount,
                onClick = { onIncrement(count) },
                onLongClick = { onIncrement(count) }
            ),
            onClick = { if (count < maxCount) onIncrement(count) },
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
        }
    }
}