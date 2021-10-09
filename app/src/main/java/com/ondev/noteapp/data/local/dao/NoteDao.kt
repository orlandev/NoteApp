package com.ondev.noteapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ondev.noteapp.data.local.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(noteEntity: NoteEntity)

    @Update
    suspend fun update(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id=:id")
    suspend fun getNoteById(id: Int): NoteEntity

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<NoteEntity>>

}