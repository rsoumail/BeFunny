package com.rsoumail.befunny.feature.funnyreal.domain.repository

import com.rsoumail.befunny.feature.funnyreal.domain.model.Funny
import kotlinx.coroutines.flow.Flow

interface FunnyRepository {
    suspend fun post(funny: Funny)

    suspend fun get(): Flow<List<Funny>>

    suspend fun save(funny: Funny)
}