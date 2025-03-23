package com.rsoumail.befunny.feature.funnyreal.ui.common.model

data class Funny(
    val id: Int? = 0,
    val location: String,
    val likes: Int = 0,
    val metaData: MetaData? = null
)