package com.rsoumail.befunny.feature.funnyreal.data.repository

import android.content.Context
import com.rsoumail.befunny.core.common.DefaultDispatcherProvider
import com.rsoumail.befunny.core.common.DispatcherProvider
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FileRepository
import kotlinx.coroutines.withContext
import java.io.File

class FileRepositoryImpl(
    private val context: Context,
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

    override suspend fun create(name: String): File {
        return withContext(dispatchers.io()) {
            File(context.filesDir, name)
        }
    }
}