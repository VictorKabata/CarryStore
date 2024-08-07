package com.vickbt.carrystore.di

import com.vickbt.carrystore.data.cache.sqldelight.daos.CartDao
import com.vickbt.carrystore.data.datasources.CartRepositoryImpl
import com.vickbt.carrystore.data.datasources.ProductsRepository
import com.vickbt.carrystore.data.network.ApiService
import com.vickbt.carrystore.data.network.ApiServiceImpl
import com.vickbt.carrystore.data.network.NetworkClient
import com.vickbt.carrystore.domain.repositories.CartRepository
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

    singleOf(::ProductsRepository)
    single<CartRepository>{CartRepositoryImpl(cartDao = get())}
    singleOf(::CartRepositoryImpl)

    viewModelOf(::MainScreenViewModel)
    viewModelOf(::ProductsViewModel)
    viewModelOf(::CartViewModel)
}

expect fun platformModule(): Module
