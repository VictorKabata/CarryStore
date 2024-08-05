package com.vickbt.carrystore.di

import com.vickbt.carrystore.data.cache.sqldelight.daos.CartDao
import com.vickbt.carrystore.data.datasources.CartRepository
import com.vickbt.carrystore.data.datasources.ProductsRepository
import com.vickbt.carrystore.data.network.ApiService
import com.vickbt.carrystore.data.network.NetworkClient
import com.vickbt.carrystore.ui.screens.cart.CartViewModel
import com.vickbt.carrystore.ui.screens.main.MainScreenViewModel
import com.vickbt.carrystore.ui.screens.product_details.ProductDetailsViewModel
import com.vickbt.carrystore.ui.screens.products.ProductsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {

    single { NetworkClient.httpClient }

    singleOf(::ApiService)

    singleOf(::CartDao)

    singleOf(::ProductsRepository)
    singleOf(::CartRepository)

    viewModelOf(::MainScreenViewModel)
    viewModelOf(::ProductsViewModel)
    viewModelOf(::ProductDetailsViewModel)
    viewModelOf(::CartViewModel)
}

expect fun platformModule(): Module