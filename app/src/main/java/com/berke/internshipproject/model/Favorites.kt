package com.berke.internshipproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berke.internshipproject.model.Favorites.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Favorites(
    @ColumnInfo(name = COLUMN_ID) @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = COLUMN_URL) val url: String,
) {

    companion object {
        const val TABLE_NAME = "Favorites"
        const val COLUMN_ID = "id"
        const val COLUMN_URL = "url"
    }

}
