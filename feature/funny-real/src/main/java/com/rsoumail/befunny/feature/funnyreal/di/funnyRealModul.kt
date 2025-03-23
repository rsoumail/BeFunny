package com.rsoumail.befunny.feature.funnyreal.di

import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
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
import com.rsoumail.befunny.feature.funnyreal.domain.usecase.VoteOnFunnyUseCase
import com.rsoumail.befunny.feature.funnyreal.ui.player.PlayerViewModel
import com.rsoumail.befunny.feature.funnyreal.ui.recorder.RecorderViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

const val FUNNY_DATABASE_NAME = "be_funny_real"

val funnyRealModule = module {
    viewModel {
        PlayerViewModel(
            player = get(),
            fileRepository = get(),
            publishFunnyUseCase = get(),
            voteOnFunnyUseCase = get()
        )
    }

    viewModel {
        RecorderViewModel(
            fileRepository = get(),
            getFunniesUseCase = get(),
            controller = get(),
            executor = get()
        )
    }

    factory { ContextCompat.getMainExecutor(androidContext()) }

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

    factory {
        VoteOnFunnyUseCase(
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

    single<LifecycleCameraController> {
        LifecycleCameraController(androidContext()).apply {
            setEnabledUseCases(CameraController.VIDEO_CAPTURE)
        }
    }

    factory<ExoPlayer> {
        ExoPlayer.Builder(androidApplication()).build().apply {
            repeatMode = Player.REPEAT_MODE_ONE
        }
    }
}