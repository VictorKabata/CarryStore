package com.vickbt.carrystore.di

import com.vickbt.carrystore.data.datasources.ProductsRepository
import com.vickbt.carrystore.data.network.ApiService
import com.vickbt.carrystore.data.network.NetworkClient
import com.vickbt.carrystore.ui.screens.products.ProductsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {

    single { NetworkClient.httpClient }

    singleOf(::ApiService)
    singleOf(::ProductsRepository)

    viewModelOf(::ProductsViewModel)
}

expect fun platformModule(): Module