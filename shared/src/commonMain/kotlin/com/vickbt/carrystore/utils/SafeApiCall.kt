package com.vickbt.carrystore.utils

suspend fun <T : Any?> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.success(apiCall.invoke())
    } catch (e: Exception) {
        Result.failure(e)
    }
}
