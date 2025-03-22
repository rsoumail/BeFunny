package com.rsoumail.befunny.feature.funnyreal.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rsoumail.befunny.feature.funnyreal.data.model.UserFunny
import kotlinx.coroutines.flow.Flow

@Dao
interface FunnyDao {
    @Query("SELECT * FROM funnies")
    fun getAll(): Flow<List<UserFunny>>

    @Insert
    suspend fun insert(funny: UserFunny)
}