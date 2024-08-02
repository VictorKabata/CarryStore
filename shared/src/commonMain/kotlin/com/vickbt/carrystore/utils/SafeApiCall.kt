package com.vickbt.carrystore.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun <T : Any?> safeApiCall(apiCall: suspend () -> T): Flow<Result<T>> = flow {
    try {
        Result.success(apiCall.invoke())
    } catch (e: Exception) {
        Result.failure(e)
    }
}
