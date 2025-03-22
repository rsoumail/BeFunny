package com.rsoumail.befunny.feature.funnyreal.data.repository

import com.rsoumail.befunny.core.common.DefaultDispatcherProvider
import com.rsoumail.befunny.core.common.DispatcherProvider
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FileRepository
import kotlinx.coroutines.withContext
import java.io.File

class FileRepositoryImpl(
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : FileRepository{
    override suspend fun delete(path: String) {
        withContext(dispatchers.io()){
            try {
                File(path).delete()
            } catch (e: Exception) {
                // Todo Log exception
            }
        }
    }
}