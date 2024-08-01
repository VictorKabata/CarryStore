package com.vickbt.carrystore.di

import com.vickbt.carrystore.data.network.NetworkClient
import org.koin.dsl.module

val commonModule = module {
    
    single { NetworkClient.httpClient }
}