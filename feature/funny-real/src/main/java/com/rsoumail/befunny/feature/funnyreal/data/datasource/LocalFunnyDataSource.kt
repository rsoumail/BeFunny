package com.rsoumail.befunny.feature.funnyreal.data.datasource

import com.rsoumail.befunny.feature.funnyreal.data.model.UserFunny
import kotlinx.coroutines.flow.Flow

interface LocalFunnyDataSource {
    suspend fun save(funny: UserFunny)

    suspend fun get(): Flow<List<UserFunny>>
}