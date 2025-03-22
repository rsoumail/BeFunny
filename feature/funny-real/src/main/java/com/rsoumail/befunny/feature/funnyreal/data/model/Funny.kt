package com.rsoumail.befunny.feature.funnyreal.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class Funny(
    open val id: Int = 0,
    open val location: String
)

data class RemoteFunny(
    override val id: Int,
    override val location: String,
    val likes: Int
): Funny(id, location)

@Entity("funnies")
data class UserFunny(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    @ColumnInfo(name = "location")
    override val location: String
): Funny(id, location)
