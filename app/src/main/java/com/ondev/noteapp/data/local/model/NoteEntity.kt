package com.ondev.noteapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    val title: String,
    val date: Long,
    val message: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}