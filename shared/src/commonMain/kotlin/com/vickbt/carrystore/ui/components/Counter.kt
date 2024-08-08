@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.vickbt.carrystore.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import carrystore.shared.generated.resources.Res
import carrystore.shared.generated.resources.nunito
import org.jetbrains.compose.resources.Font

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
                .testTag("button_decrement")
                .size(countButtonSize)
                .combinedClickable(
                    onClick = { onDecrement(count) },
                    onLongClick = { onDecrement(count) }
                )
                .clip(CircleShape),
            enabled = count > 1,
            colors = IconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            onClick = { onDecrement(count) }
        ) {
            Icon(
                imageVector = Icons.Rounded.Remove,
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.testTag("text_count").padding(horizontal = 8.dp),
            text = count.toString(),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            fontSize = countTextSize,
            fontFamily = FontFamily(Font(Res.font.nunito))
        )

        IconButton(
            modifier = Modifier
                .testTag("button_increment")
                .size(countButtonSize)
                .combinedClickable(
                    onClick = { onIncrement(count) },
                    onLongClick = { onIncrement(count) }
                )
                .clip(CircleShape),
            enabled = count < maxCount,
            colors = IconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            onClick = { onIncrement(count) }
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null
            )
        }
    }
}
