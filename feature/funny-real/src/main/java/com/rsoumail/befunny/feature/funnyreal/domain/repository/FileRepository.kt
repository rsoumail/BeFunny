package com.rsoumail.befunny.feature.funnyreal.domain.repository

import java.io.File

interface FileRepository {

    suspend fun delete(path: String)

    suspend fun create(name: String): File
}