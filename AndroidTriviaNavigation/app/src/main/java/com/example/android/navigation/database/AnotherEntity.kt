package com.example.android.navigation.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vmi")
data class AnotherEntity (
        @PrimaryKey(autoGenerate = true)
        var matchId: Long = 0L,

        @ColumnInfo(name = "vmi")
        var name1: String = "vmi"

)

