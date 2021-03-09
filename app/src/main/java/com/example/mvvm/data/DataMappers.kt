package com.example.mvvm.data

import com.example.mvvm.data.db.MovieDB
import com.example.mvvm.data.domain.MovieModel
import com.example.mvvm.data.server.MovieServer


fun MovieModel.toRoomMovie(): MovieDB =
    MovieDB(
        id,
        imageUrl,
        title,
        isFavorite
    )

fun MovieDB.toDomainMovie(): MovieModel =
    MovieModel(
        id,
        imageUrl,
        title,
        isFavorite
    )

fun MovieServer.toDomainMovie(): MovieModel =
    MovieModel(
        id,
        imageUrl,
        title,
        isFavorite
    )
