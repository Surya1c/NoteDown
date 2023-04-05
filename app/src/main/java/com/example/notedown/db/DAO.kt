package com.example.notedown.db

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  addNote(note: com.example.notedown.model.Note)

    @Update
    suspend fun updateNote(note: com.example.notedown.model.Note)

    @Query(value = "SELECT * FROM Note ORDER BY id DESC")
    fun getAllNote(): LiveData<List<Note>>



    @Query(value = "SELECT * FROM Note WHERE title LIKE :query OR content LIKE :query OR date LIKE :query OR date LIKE :query ORDER BY id DESC")
    fun searchNote(query: String): LiveData<List<Note>>


    @Delete
    suspend fun deleteNote(note: com.example.notedown.model.Note)
}