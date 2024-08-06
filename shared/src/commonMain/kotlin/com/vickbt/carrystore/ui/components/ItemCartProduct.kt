package com.vickbt.carrystore.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import carrystore.shared.generated.resources.Res
import carrystore.shared.generated.resources.nunito
import coil3.compose.AsyncImage
import com.vickbt.carrystore.domain.models.Product
import org.jetbrains.compose.resources.Font

@Composable
fun ItemCartProduct(
    modifier: Modifier = Modifier,
    product: Product,
    onItemCountChanged: (Int) -> Unit,
    onClickDelete: (Int) -> Unit
) {
    Card(modifier = modifier, shape = MaterialTheme.shapes.small) {
        Row(
            modifier = Modifier.fillMaxSize().padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(.30f).weight(3f),
                model = product.imageLocation,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )

            Column(
                modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp, vertical = 16.dp)
                    .weight(6f),
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier,
                    text = product.name,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily(Font(Res.font.nunito))
                )

                Text(
                    modifier = Modifier,
                    text = "${product.currencyCode} ${product.price * (product.cartQuantity ?: 1)} ",
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily(Font(Res.font.nunito))
                )

                Spacer(modifier = Modifier.height(6.dp))

                Counter(
                    modifier = Modifier,
                    count = product.cartQuantity ?: 1,
                    maxCount = product.quantity,
                    countButtonSize = 30.dp,
                    countTextSize = 14.sp,
                    onIncrement = { onItemCountChanged(it.plus(1)) },
                    onDecrement = { onItemCountChanged(it.minus(1)) }
                )
            }

            IconButton(modifier = Modifier.weight(1f), onClick = { onClickDelete(product.id) }) {
                Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
            }
        }
    }
}
