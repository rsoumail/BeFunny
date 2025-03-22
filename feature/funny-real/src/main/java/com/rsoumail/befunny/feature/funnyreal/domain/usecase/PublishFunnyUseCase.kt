package com.rsoumail.befunny.feature.funnyreal.domain.usecase

import com.rsoumail.befunny.feature.funnyreal.domain.model.Funny
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FunnyRepository

class PublishFunnyUseCase(
    private val funnyRepository: FunnyRepository
) {
    suspend operator fun invoke(funny: Funny) {
        funnyRepository.save(funny).let {
            funnyRepository.post(funny)
        }
    }
}