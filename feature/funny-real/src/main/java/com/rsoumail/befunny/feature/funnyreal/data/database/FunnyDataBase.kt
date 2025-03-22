package com.rsoumail.befunny.feature.funnyreal.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rsoumail.befunny.feature.funnyreal.data.model.UserFunny

@Database(
    entities = [UserFunny::class],
    version = 1,
    exportSchema = false
)
abstract class FunnyDataBase : RoomDatabase() {
    abstract fun getFunnyDao(): FunnyDao
}