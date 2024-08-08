package com.vickbt.carrystore.ui.screens.details

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import carrystore.shared.generated.resources.Res
import carrystore.shared.generated.resources.add_to_cart
import carrystore.shared.generated.resources.buy_now
import carrystore.shared.generated.resources.nunito
import coil3.compose.AsyncImage
import com.vickbt.carrystore.domain.models.Product
import com.vickbt.carrystore.ui.components.Counter
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductBottomSheet(
    modifier: Modifier = Modifier,
    product: Product,
    itemCount: Int,
    onItemCountChanged: (Int) -> Unit,
    onAddToCartClicked: (Product) -> Unit,
    onBuyNowClicked: (Product) -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    val columnScrollState = rememberScrollState()

    Box(modifier = modifier.testTag("screen_details")) {
        Column(
            modifier = Modifier.verticalScroll(columnScrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
        ) {
            AsyncImage(
                modifier = Modifier.testTag("image_product").fillMaxSize(.40f),
                model = product.imageLocation,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )

            //region Name & Price
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier=Modifier.testTag("text_product_name"),
                    text = product.name,
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily(Font(Res.font.nunito))
                )

                Text(
                    modifier=Modifier.testTag("text_product_price"),
                    text = "${product.currencyCode} ${product.price}",
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily(Font(Res.font.nunito))
                )
            }
            //endregion

            //region Description title & Description
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier,
                    text = "Description",
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily(Font(Res.font.nunito))
                )

                Text(
                    modifier=Modifier.testTag("text_product_description"),
                    text = product.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .85f),
                    fontFamily = FontFamily(Font(Res.font.nunito))
                )
            }
            //endregion

            Counter(
                modifier = Modifier.testTag("counter").fillMaxWidth(.80f),
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
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
            ) {
                Button(
                    modifier = Modifier.testTag("button_add_to_cart").fillMaxWidth().padding(vertical = 8.dp),
                    onClick = { onAddToCartClicked(product) },
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 6.dp),
                        text = stringResource(Res.string.add_to_cart),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = FontFamily(Font(Res.font.nunito))
                    )
                }

                Button(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    onClick = { onBuyNowClicked(product) },
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 6.dp),
                        text = stringResource(Res.string.buy_now),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = FontFamily(Font(Res.font.nunito))
                    )
                }
            }
            //endregion
        }
    }
}
