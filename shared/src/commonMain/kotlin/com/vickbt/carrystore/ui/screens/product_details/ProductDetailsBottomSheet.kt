@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.carrystore.ui.screens.product_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.ui.components.Counter
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ProductBottomSheet(
    modifier: Modifier = Modifier,
    product: Product,
    itemCount: Int,
    onItemCountChanged: (Int) -> Unit,
    onAddToCartClicked: (Product) -> Unit,
    onBuyNowClicked: (Product) -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {

    val columnScrollState = rememberScrollState()

    Box(modifier = modifier) {
        Column(
            modifier = Modifier.verticalScroll(columnScrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(.40f),
                model = product.imageLocation,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )

            //region Name & Price
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.name,
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "${product.currencyCode} ${product.price}",
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            //endregion

            //region Description title & Description
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    text = "Description",
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = product.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .85f)
                )
            }
            //endregion

            Spacer(modifier = Modifier.height(16.dp))

            Counter(
                modifier = Modifier.fillMaxWidth(.80f),
                count = itemCount,
                maxCount = product.quantity,
                onIncrement = {
                    onItemCountChanged(itemCount.plus(1))
                    onIncrement()
                },
                onDecrement = {
                    onItemCountChanged(itemCount.minus(1))
                    onDecrement()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            //region Add to Cart & Buy Now
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    onClick = { onAddToCartClicked(product) },
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 6.dp),
                        text = "Add to Cart",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Button(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    onClick = { onBuyNowClicked(product) },
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 6.dp),
                        text = "Buy Now",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            //endregion
        }

    }
}