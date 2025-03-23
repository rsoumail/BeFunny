package com.rsoumail.befunny.feature.funnyreal.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FileRepository
import com.rsoumail.befunny.feature.funnyreal.domain.usecase.PublishFunnyUseCase
import com.rsoumail.befunny.feature.funnyreal.domain.usecase.VoteOnFunnyUseCase
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.Funny
import kotlinx.coroutines.launch
import com.rsoumail.befunny.feature.funnyreal.domain.model.Funny as DomainFunny

class PlayerViewModel(
    private val player: ExoPlayer,
    private val fileRepository: FileRepository,
    private val publishFunnyUseCase: PublishFunnyUseCase,
    private val voteOnFunnyUseCase: VoteOnFunnyUseCase
): ViewModel() {

    init {
        player.prepare()
        player.playWhenReady = true
    }

    fun publishFunny(funny: Funny) {
        viewModelScope.launch {
            publishFunnyUseCase(
                DomainFunny(location = funny.location)
            )
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

    fun vote(funny: Funny) {
        viewModelScope.launch {
            voteOnFunnyUseCase(
                DomainFunny(
                    id = funny.id,
                    location = funny.location,
                    likes = funny.likes
                )
            )
        }
    }

    fun addVideoLocation(location: String) {
        player.setMediaItem(
            MediaItem.fromUri(location)
        )
        player.prepare()
        player.playWhenReady = true
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}