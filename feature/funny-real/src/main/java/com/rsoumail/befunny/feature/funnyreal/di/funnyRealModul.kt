package com.rsoumail.befunny.feature.funnyreal.di

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.rsoumail.befunny.feature.funnyreal.ui.player.FunnyPlayerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val funnyRealModule = module {
    viewModel {
        FunnyPlayerViewModel(
            player = get()
        )
    }

    single<ExoPlayer> {
        ExoPlayer.Builder(androidApplication()).build().apply {
            repeatMode = Player.REPEAT_MODE_ONE
        }
    }
}