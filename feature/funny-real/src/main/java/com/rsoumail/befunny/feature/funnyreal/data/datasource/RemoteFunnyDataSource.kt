package com.rsoumail.befunny.feature.funnyreal.data.datasource

interface RemoteFunnyDataSource {

    suspend fun upload()

    suspend fun get()
}