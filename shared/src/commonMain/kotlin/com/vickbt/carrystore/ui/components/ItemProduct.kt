package com.vickbt.carrystore.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Card
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
fun ItemProduct(
    modifier: Modifier = Modifier,
    cardCornerRadius: CornerBasedShape = MaterialTheme.shapes.small,
    product: Product,
    onClick: (Product) -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick(product) },
        shape = cardCornerRadius
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxHeight(.60f).fillMaxWidth(),
                model = product.imageLocation,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = product.name,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontFamily = FontFamily(Font(Res.font.nunito))
                )

                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = "${product.currencyCode} ${product.price}",
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily(Font(Res.font.nunito))
                )
            }
        }
    }
}
