package com.rsoumail.befunny.core.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface DispatcherProvider {
    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined

    suspend fun onMain(block: suspend () -> Unit) {
        withContext(main()) {
            block.invoke()
        }
    }

    suspend fun onDefault(block: suspend () -> Unit) {
        withContext(default()) {
            block.invoke()
        }
    }

    suspend fun onIo(block: suspend () -> Unit) {
        withContext(io()) {
            block.invoke()
        }
    }
}

class DefaultDispatcherProvider : DispatcherProvider