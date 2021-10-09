package com.ondev.noteapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ondev.noteapp.data.local.NoteRepository
import com.ondev.noteapp.data.local.model.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val noteRepository: NoteRepository

) : ViewModel() {

    val allNotes = noteRepository.allNotes

    fun onInsert(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insert(noteEntity = noteEntity)
        }
    }

    fun onUpdate(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.update(noteEntity = noteEntity)
        }
    }

    fun onDelete(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.delete(noteEntity = noteEntity)
        }
    }

    suspend fun onGetNoteById(id: Int) = noteRepository.getNoteById(id)

}