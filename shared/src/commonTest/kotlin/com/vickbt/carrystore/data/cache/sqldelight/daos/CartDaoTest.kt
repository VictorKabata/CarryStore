package com.vickbt.carrystore.data.cache.sqldelight.daos

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.isNullOrEmpty
import com.vickbt.carrystore.utils.ProductHelper
import com.vickbt.carrystore.utils.createTestDriver
import com.vickbt.shared.data.cache.sqldelight.AppDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class CartDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var cartDao: CartDao

    @BeforeTest
    fun setup() {
        val databaseDriver = createTestDriver()
        database = AppDatabase(databaseDriver)
        cartDao = CartDao(sqlDriver = databaseDriver)
    }

    @AfterTest
    fun teardown() {
        database.appDatabaseQueries.deleteAllProducts() // Clear db after every test run
    }

    @Test
    fun `saveProduct saves product`() = runTest {
        val product = ProductHelper.product

        cartDao.saveProduct(product)

        //Then
        val savedProduct = cartDao.getProduct(product.id).first()

        assertThat(savedProduct?.id).isEqualTo(product.id.toLong())
        assertThat(savedProduct?.name).isEqualTo(product.name)
    }

    @Test
    fun `getAllProducts returns all products`() = runTest {
        val products = listOf(ProductHelper.product, ProductHelper.product, ProductHelper.product)

        products.forEach { cartDao.saveProduct(it) }

        //Then
        cartDao.getAllProducts().test {
            assertThat(products.size).isEqualTo(awaitItem().size)
        }
    }

    @Test
    fun `getProduct returns correct product`() = runTest {
        val products = listOf(ProductHelper.product, ProductHelper.product, ProductHelper.product)
        products.forEach { cartDao.saveProduct(it) }

        val response = products.map { product ->
            cartDao.getProduct(product.id)
        }

        response.forEachIndexed { index, product ->
            product.test {
                assertThat(products[index].id).isEqualTo(awaitItem()?.id?.toInt())
            }
        }
    }

    @Test
    fun `deleteProduct deletes correct product`() = runTest {
        val product = ProductHelper.product
        cartDao.saveProduct(product)

        cartDao.getProduct(id = product.id).test {
            assertThat(product.id).isEqualTo(awaitItem()?.id?.toInt())
        }

        cartDao.deleteProduct(id = product.id)

        cartDao.getProduct(id = product.id).test {
            assertThat(awaitItem()).isNull()
        }
    }

    @Test
    fun `deleteAllProducts deletes all products`() = runTest {
        val products = listOf(ProductHelper.product, ProductHelper.product, ProductHelper.product)
        products.forEach { cartDao.saveProduct(it) }

        products.forEachIndexed { index, product ->
            cartDao.getProduct(id = product.id).test {
                assertThat(products[index].id).isEqualTo(awaitItem()?.id?.toInt())
            }
        }

        cartDao.deleteAllProducts()

        cartDao.getAllProducts().test {
            assertThat(awaitItem()).isNullOrEmpty()
        }
    }

}