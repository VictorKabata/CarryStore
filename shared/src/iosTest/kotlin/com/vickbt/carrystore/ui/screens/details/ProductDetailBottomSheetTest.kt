@file:OptIn(ExperimentalTestApi::class, ExperimentalTestApi::class)

package com.vickbt.carrystore.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.vickbt.carrystore.domain.models.Product
import kotlin.random.Random
import kotlin.test.Test

class ProductDetailBottomSheetTest {

    @Test
    fun test_initial_state() = runComposeUiTest {
        val product = Product(
            id = Random.nextInt(),
            name = Random.nextInt().toString(),
            description = Random.nextInt().toString(),
            price = Random.nextInt(),
            currencyCode = Random.nextInt().toString(),
            currencySymbol = Random.nextInt().toString(),
            quantity = Random.nextInt(),
            imageLocation = Random.nextInt().toString(),
            status = Random.nextInt().toString()
        )
        var itemCount by mutableStateOf(1)

        setContent {
            ProductBottomSheet(
                product = product,
                itemCount = itemCount,
                onIncrement = { itemCount + 1 },
                onDecrement = { itemCount - 1 }
            )
        }

        onNodeWithTag("screen_details").assertIsDisplayed()
        onNodeWithTag("text_product_name").assertTextEquals(product.name)
        onNodeWithTag("text_product_price").assertTextEquals("${product.currencyCode} ${product.price}")
        onNodeWithTag("text_product_description").assertTextEquals(product.description)
        onNodeWithTag("text_count").assertTextEquals(itemCount.toString())
    }
}
