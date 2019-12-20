package com.example.android.navigation.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eredmenyek")
data class EredmenyEntity (
        @PrimaryKey(autoGenerate = true)
        var matchId: Long = 0L,

        @ColumnInfo(name = "name1")
        var name1: String = "unknown1",

        @ColumnInfo(name = "name2")
        var name2: String = "unknown2",

        @ColumnInfo(name = "pont")
        var pont: Int = -1
)

