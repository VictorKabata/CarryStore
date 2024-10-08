package com.vickbt.carrystore.di

import com.vickbt.carrystore.data.cache.sqldelight.daos.CartDao
import com.vickbt.carrystore.data.datasources.CartRepositoryImpl
import com.vickbt.carrystore.data.datasources.ProductsRepositoryImpl
import com.vickbt.carrystore.data.network.ApiService
import com.vickbt.carrystore.data.network.ApiServiceImpl
import com.vickbt.carrystore.data.network.NetworkClient
import com.vickbt.carrystore.domain.repositories.CartRepository
import com.vickbt.carrystore.domain.repositories.ProductsRepository
import com.vickbt.carrystore.ui.screens.cart.CartViewModel
import com.vickbt.carrystore.ui.screens.main.MainScreenViewModel
import com.vickbt.carrystore.ui.screens.products.ProductsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {

    single { NetworkClient.httpClient }

    single<ApiService> { ApiServiceImpl(httpClient = get()) }

    singleOf(::CartDao)

    single<ProductsRepository> { ProductsRepositoryImpl(apiService = get()) }
    single<CartRepository> { CartRepositoryImpl(cartDao = get()) }

    viewModelOf(::MainScreenViewModel)
    viewModelOf(::ProductsViewModel)
    viewModelOf(::CartViewModel)
}

expect fun platformModule(): Module
