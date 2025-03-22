package com.rsoumail.befunny.feature.funnyreal.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.rsoumail.befunny.core.common.DefaultDispatcherProvider
import com.rsoumail.befunny.core.common.DispatcherProvider
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FileRepository
import com.rsoumail.befunny.feature.funnyreal.domain.usecase.PublishFunnyUseCase
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.Funny
import com.rsoumail.befunny.feature.funnyreal.domain.model.Funny as DomainFunny
import kotlinx.coroutines.launch

class FunnyPlayerViewModel(
    private val player: ExoPlayer,
    private val fileRepository: FileRepository,
    private val publishFunnyUseCase: PublishFunnyUseCase,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
): ViewModel() {

    fun publishFunny(funny: Funny) {
        viewModelScope.launch {
            publishFunnyUseCase(DomainFunny(location = funny.location))
        }
    }

    fun player(): ExoPlayer {
        return player
    }

    fun cancel(location: String) {
        viewModelScope.launch {
            fileRepository.delete(location)
        }
    }
}