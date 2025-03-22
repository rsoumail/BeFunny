package com.rsoumail.befunny.feature.funnyreal.domain.repository

interface FileRepository {

    suspend fun delete(path: String)
}