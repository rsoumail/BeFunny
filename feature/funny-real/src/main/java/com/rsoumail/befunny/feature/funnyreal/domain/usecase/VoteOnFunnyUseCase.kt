package com.rsoumail.befunny.feature.funnyreal.domain.usecase

import com.rsoumail.befunny.feature.funnyreal.domain.model.Funny
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FunnyRepository

class VoteOnFunnyUseCase(
    private val funnyRepository: FunnyRepository
) {
    suspend operator fun invoke(funny: Funny) {
        funnyRepository.post(funny)
    }
}