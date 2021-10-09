package com.ondev.noteapp.data.local

import androidx.lifecycle.LiveData
import com.ondev.noteapp.data.local.dao.NoteDao
import com.ondev.noteapp.data.local.model.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    val allNotes: Flow<List<NoteEntity>> = noteDao.getAllNotes()

    suspend fun insert(noteEntity: NoteEntity) {
        noteDao.insert(noteEntity = noteEntity)
    }

    suspend fun update(noteEntity: NoteEntity) {
        noteDao.update(noteEntity = noteEntity)
    }

    suspend fun delete(noteEntity: NoteEntity) {
        noteDao.delete(noteEntity = noteEntity)
    }

    suspend fun getNoteById(id: Int) = noteDao.getNoteById(id)

}