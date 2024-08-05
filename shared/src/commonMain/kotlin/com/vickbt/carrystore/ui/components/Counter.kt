@file:OptIn(ExperimentalFoundationApi::class)

package com.vickbt.carrystore.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    count: Int = 1,
    maxCount: Int,
    onIncrement: (Int) -> Unit,
    onDecrement: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        FloatingActionButton(
            modifier = Modifier.combinedClickable(
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
                imageVector = if (count == 1) Icons.Rounded.Delete else Icons.Rounded.Remove,
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier,
            text = count.toString(),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            fontWeight = FontWeight.Bold
        )

        FloatingActionButton(
            modifier = Modifier.combinedClickable(
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