package com.example.android.navigation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [EredmenyEntity::class, AnotherEntity::class] , version = 2, exportSchema = false)
abstract class EredmenyekRoom : RoomDatabase() {

    abstract val eredmenyDao: EredmenyDAO

    companion object{
        @Volatile
        private var INSTANCE: EredmenyekRoom? = null

        fun getInstance(context: Context): EredmenyekRoom {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            EredmenyekRoom::class.java,
                            "eredmenyek"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}