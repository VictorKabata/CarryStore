package com.vickbt.carrystore.di

import com.vickbt.carrystore.data.network.NetworkClient
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    
    single { NetworkClient.httpClient }
}

expect fun platformModule(): Module