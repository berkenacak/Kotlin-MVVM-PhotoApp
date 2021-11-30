package com.berke.internshipproject.network.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.berke.internshipproject.model.Favorites

@Database(entities = [Favorites::class], version = 1, exportSchema = false)
abstract class FavoritesRoom : RoomDatabase() {

    companion object {

        private const val DB_NAME = "favorites_db"

        /**
         * Prevent multiple instances from occurring with singleton structure
         */
        @Volatile
        private var INSTANCE: FavoritesRoom? = null

        fun getDatabase(context: Context): FavoritesRoom {
            return when (val tempInstance = INSTANCE) {
                null -> synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context,
                        FavoritesRoom::class.java,
                        DB_NAME
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries().build()
                    INSTANCE = instance

                    return instance
                }
                else -> tempInstance
            }
        }
    }

    abstract fun favoritesDao(): FavoritesDAO
}
