package com.rsoumail.befunny.feature.funnyreal.domain.usecase

import com.rsoumail.befunny.feature.funnyreal.domain.model.Funny
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FunnyRepository
import kotlinx.coroutines.flow.Flow

class GetFunniesUseCase(
    private val funnyRepository: FunnyRepository
) {
    suspend operator fun invoke(): Flow<List<Funny>> {
        return funnyRepository.get()
    }
}