package com.vickbt.carrystore.utils

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger

fun PlatformContext.getAsyncImageLoader() = ImageLoader.Builder(this)
    .crossfade(true)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .crossfade(true)
    .memoryCache {
        MemoryCache.Builder().maxSizePercent(this, 0.3).strongReferencesEnabled(true).build()
    }
    .logger(DebugLogger())
    .build()
