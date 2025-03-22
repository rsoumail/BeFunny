package com.rsoumail.befunny.feature.funnyreal.di

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Room
import com.rsoumail.befunny.feature.funnyreal.data.database.FunnyDao
import com.rsoumail.befunny.feature.funnyreal.data.database.FunnyDataBase
import com.rsoumail.befunny.feature.funnyreal.data.datasource.LocalFunnyDataSource
import com.rsoumail.befunny.feature.funnyreal.data.datasource.LocalFunnyDataSourceImpl
import com.rsoumail.befunny.feature.funnyreal.data.repository.FileRepositoryImpl
import com.rsoumail.befunny.feature.funnyreal.data.repository.FunnyRepositoryImpl
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FileRepository
import com.rsoumail.befunny.feature.funnyreal.domain.repository.FunnyRepository
import com.rsoumail.befunny.feature.funnyreal.domain.usecase.GetFunniesUseCase
import com.rsoumail.befunny.feature.funnyreal.domain.usecase.PublishFunnyUseCase
import com.rsoumail.befunny.feature.funnyreal.ui.player.FunnyPlayerViewModel
import com.rsoumail.befunny.feature.funnyreal.ui.recorder.FunnyRecorderViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

const val FUNNY_DATABASE_NAME = "be_funny_real"

val funnyRealModule = module {
    viewModel {
        FunnyPlayerViewModel(
            player = get(),
            fileRepository = get(),
            publishFunnyUseCase = get()
        )
    }

    viewModel {
        FunnyRecorderViewModel(
            fileRepository = get()
        )
    }

    factory {
        PublishFunnyUseCase(
            funnyRepository = get()
        )
    }

    factory {
        GetFunniesUseCase(
            funnyRepository = get()
        )
    }

    single<FunnyRepository> {
        FunnyRepositoryImpl(
            localFunnyDataSource = get()
        )
    }

    single<FileRepository> {
        FileRepositoryImpl(
            context = androidApplication()
        )
    }

    single<LocalFunnyDataSource> {
        LocalFunnyDataSourceImpl(funnyDao = get())
    }

    single<FunnyDao> {
        get<FunnyDataBase>().getFunnyDao()
    }

    single<FunnyDataBase> {
        Room.databaseBuilder(
            androidApplication(),
            FunnyDataBase::class.java,
            FUNNY_DATABASE_NAME
        ).build()
    }

    single<ExoPlayer> {
        ExoPlayer.Builder(androidApplication()).build().apply {
            repeatMode = Player.REPEAT_MODE_ONE
        }
    }
}