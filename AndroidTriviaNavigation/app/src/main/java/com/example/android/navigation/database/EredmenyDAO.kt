package com.example.android.navigation.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EredmenyDAO {

    @Insert
    fun insert(eredmeny: EredmenyEntity)

    @Update
    fun update(eredmeny: EredmenyEntity)

    @Query("SELECT * from eredmenyek WHERE matchId = :key")
    fun get(key: Long): EredmenyEntity?

    @Query("DELETE FROM eredmenyek")
    fun clear()

    @Query("SELECT * FROM eredmenyek ORDER BY matchId DESC LIMIT 1")
    fun getEredmeny(): EredmenyEntity?

    @Query("SELECT * FROM eredmenyek ORDER BY matchId DESC")
    fun getAllEredmeny(): LiveData<List<EredmenyEntity>>

    @Query("SELECT * FROM eredmenyek ORDER BY matchId DESC LIMIT 1")
    fun getLast(): EredmenyEntity?


}