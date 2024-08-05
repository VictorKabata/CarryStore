package com.vickbt.carrystore.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vickbt.carrystore.domain.models.Product

@Composable
fun ItemCartProduct(
    modifier: Modifier = Modifier,
    product: Product,
    onClickDelete: (Int) -> Unit,
    onClick: (Product) -> Unit
) {

    Card(modifier = modifier.clickable { onClick(product) }, shape = MaterialTheme.shapes.medium) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(.20f).weight(3f),
                model = product.imageLocation,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )

            Column(
                modifier = Modifier.fillMaxHeight().padding(horizontal = 8.dp).weight(6f),
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier,
                    text = product.name,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    modifier = Modifier,
                    text = "${product.currencySymbol} ${product.price} ",
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(modifier = Modifier.weight(1f), onClick = { onClickDelete(product.id) }) {
                Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
            }

        }
    }
}