package com.rsoumail.befunny.feature.funnyreal.di

import com.rsoumail.befunny.feature.funnyreal.ui.player.FunnyPlayerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val funnyRealModule = module {
    viewModel {
        FunnyPlayerViewModel()
    }
}