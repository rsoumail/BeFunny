package com.rsoumail.befunny.feature.funnyreal.data.datasource

import com.rsoumail.befunny.feature.funnyreal.data.database.FunnyDao
import com.rsoumail.befunny.feature.funnyreal.data.model.UserFunny
import kotlinx.coroutines.flow.Flow

class LocalFunnyDataSourceImpl(
    private val funnyDao: FunnyDao
) : LocalFunnyDataSource {
    override suspend fun save(funny: UserFunny) {
        funnyDao.insert(funny)
    }

    override suspend fun get(): Flow<List<UserFunny>> {
        return funnyDao.getAll()
    }
}