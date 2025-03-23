package com.rsoumail.befunny.feature.funnyreal.data.repository

import com.rsoumail.befunny.core.common.DefaultDispatcherProvider
import com.rsoumail.befunny.core.common.DispatcherProvider
import com.rsoumail.befunny.feature.funnyreal.data.datasource.LocalFunnyDataSource
import com.rsoumail.befunny.feature.funnyreal.data.model.UserFunny
import com.rsoumail.befunny.feature.funnyreal.domain.model.Funny
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FunnyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FunnyRepositoryImpl(
    private val localFunnyDataSource: LocalFunnyDataSource,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : FunnyRepository {
    override suspend fun post(funny: Funny) {
        // Todo("Not yet implemented")
    }

    override suspend fun get(): Flow<List<Funny>> {
        return flow {
            localFunnyDataSource.get().collect { funnies ->
                val domainFunnies = arrayListOf<Funny>()
                funnies.forEach { funny ->
                    domainFunnies.add(
                        Funny(
                            id = funny.id,
                            location = funny.location
                        )
                    )
                }
                emit(domainFunnies)
            }
        }.flowOn(dispatchers.io())
    }

    override suspend fun save(funny: Funny) {
        localFunnyDataSource.save(UserFunny(location = funny.location))
    }
}