package com.example.notedown.viewmodel

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notedown.model.Note
import com.example.notedown.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteActivityViewModel(private val repository: NoteRepository) : ViewModel(){

    fun saveNote(newNote : Note) =viewModelScope.launch(Dispatchers.IO){
        repository.getAddNote(newNote)
    }

    fun updateNote(existingNote : Note) =viewModelScope.launch(Dispatchers.IO){
        repository.getUpdateNote(existingNote)
    }

    fun deleteNote(existingNote : Note) =viewModelScope.launch(Dispatchers.IO){
        repository.getDeleteNote(existingNote)}

    fun searchNote(query: String): LiveData<List<ContactsContract.CommonDataKinds.Note>> {
        return repository.searchNote(query)
    }

    fun getALlNotes() : LiveData<List<ContactsContract.CommonDataKinds.Note>> = repository.getNote();




}