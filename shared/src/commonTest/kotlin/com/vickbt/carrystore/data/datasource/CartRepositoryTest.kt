package com.vickbt.carrystore.data.datasource

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.isNullOrEmpty
import com.vickbt.carrystore.data.cache.sqldelight.daos.CartDao
import com.vickbt.carrystore.data.datasources.CartRepository
import com.vickbt.carrystore.utils.ProductHelper
import com.vickbt.carrystore.utils.createTestDriver
import com.vickbt.shared.data.cache.sqldelight.AppDatabase
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class CartRepositoryTest {

    private lateinit var cartRepository: CartRepository

    private lateinit var database: AppDatabase
    private lateinit var cartDao: CartDao

    @BeforeTest
    fun setup() {
        val databaseDriver = createTestDriver()
        database = AppDatabase(databaseDriver)
        cartDao = CartDao(sqlDriver = databaseDriver)

        cartRepository = CartRepository(cartDao)
    }

    @AfterTest
    fun teardown() {
        database.appDatabaseQueries.deleteAllProducts() // Clear db after every test run
    }

    @Test
    fun `saveProducts saves product`() = runTest {
        val products = List(5) { ProductHelper.product }
        products.forEach { cartRepository.saveProduct(it) }

        cartRepository.getAllProducts().test {
            assertThat(products.size).isEqualTo(awaitItem().size)
        }
    }

    @Test
    fun `getAllProducts returns all products`() = runTest {
        val products = List(5) { ProductHelper.product }
        products.forEach { cartRepository.saveProduct(it) }

        cartRepository.getAllProducts().test {
            assertThat(products.size).isEqualTo(awaitItem().size)
        }
    }

    @Test
    fun `getProduct returns correct product`() = runTest {
        val products = List(5) { ProductHelper.product }
        products.forEach { cartRepository.saveProduct(it) }

        products.forEachIndexed { index, product ->
            cartRepository.getProduct(product.id).test {
                assertThat(products[index].id).isEqualTo(awaitItem()?.id)
            }
        }
    }

    @Test
    fun `deleteCartProduct deletes correct product`() = runTest {
        val products = List(5) { ProductHelper.product }
        products.forEach { cartRepository.saveProduct(it) }

        cartRepository.getProduct(products.first().id).test {
            assertThat(products.first().id).isEqualTo(awaitItem()?.id)
        }

        cartRepository.deleteCartProduct(products.first().id)

        cartRepository.getProduct(products.first().id).test {
            assertThat(awaitItem()).isNull()
        }

        cartRepository.getAllProducts().test {
            assertThat(awaitItem().size).isEqualTo(4)
        }
    }

    @Test
    fun `deleteAllProducts deletes all products`() = runTest {
        val products = List(5) { ProductHelper.product }
        products.forEach { cartRepository.saveProduct(it) }

        cartRepository.getAllProducts().test {
            assertThat(products.size).isEqualTo(awaitItem().size)
        }

        cartRepository.deleteAllProducts()

        cartRepository.getAllProducts().test {
            assertThat(awaitItem()).isNullOrEmpty()
        }

    }

}