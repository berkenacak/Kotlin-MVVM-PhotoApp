package com.berke.internshipproject.di

import android.content.Context
import com.berke.internshipproject.network.local.FavoritesDAO
import com.berke.internshipproject.network.local.FavoritesRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideFavoritesDAO(@ApplicationContext context: Context): FavoritesDAO = FavoritesRoom.getDatabase(context).favoritesDao()
}