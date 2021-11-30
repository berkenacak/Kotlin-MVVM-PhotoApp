package com.berke.internshipproject.repository

import com.berke.internshipproject.model.Favorites
import com.berke.internshipproject.network.local.FavoritesDAO
import javax.inject.Inject

class DetailRepository @Inject constructor(private val favoritesDAO: FavoritesDAO) {

    suspend fun allImages(): List<Favorites> = favoritesDAO.getAll()

    suspend fun insertImage(favorites: Favorites) {
        favoritesDAO.insertImage(favorites)
    }

    suspend fun deleteImage(url: String) {
        favoritesDAO.deleteImage(url)
    }

    fun hasContains(url: String) : List<Favorites> {
        return favoritesDAO.hasContains(url)
    }

}