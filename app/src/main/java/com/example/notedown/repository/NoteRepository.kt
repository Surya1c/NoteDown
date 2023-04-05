package com.example.notedown.repository

import com.example.notedown.db.NoteDatabase
import com.example.notedown.model.Note

class NoteRepository(private val db: NoteDatabase) {

    fun getNote() = db.getNoteDao().getAllNote();

    fun searchNote(query: String)= db.getNoteDao().searchNote(query)
    suspend fun getAddNote(note: Note) = db.getNoteDao().addNote(note)
    suspend fun getUpdateNote(note: Note) = db.getNoteDao().updateNote(note)
    suspend fun getDeleteNote(note: Note) = db.getNoteDao().deleteNote(note)


}