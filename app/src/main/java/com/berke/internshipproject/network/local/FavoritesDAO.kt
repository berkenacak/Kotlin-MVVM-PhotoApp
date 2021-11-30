package com.berke.internshipproject.network.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.berke.internshipproject.model.Favorites

@Dao
abstract class FavoritesDAO {

    @Insert
    abstract suspend fun insertImage(favorite: Favorites)

    @Query("SELECT * FROM ${Favorites.TABLE_NAME}")
    abstract suspend fun getAll(): List<Favorites>

    @Query("SELECT * FROM ${Favorites.TABLE_NAME}  WHERE ${Favorites.COLUMN_URL} IN (:url)")
    abstract fun hasContains(url: String): List<Favorites>

    @Query("DELETE FROM ${Favorites.TABLE_NAME} WHERE ${Favorites.COLUMN_URL} IN (:url)")
    abstract suspend fun deleteImage(url: String)

}