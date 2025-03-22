package com.rsoumail.befunny.feature.funnyreal.ui.player

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import com.rsoumail.befunny.feature.funnyreal.ui.common.model.Funny

class FunnyPlayerViewModel(
    private val player: ExoPlayer
): ViewModel() {

    fun publishFunny(funny: Funny) {

    }

    fun player(): ExoPlayer {
        return player
    }
}