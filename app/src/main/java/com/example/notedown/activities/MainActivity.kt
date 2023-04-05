package com.example.notedown.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notedown.R
import com.example.notedown.databinding.ActivityMainBinding
import com.example.notedown.db.NoteDatabase
import com.example.notedown.repository.NoteRepository
import com.example.notedown.viewmodel.NoteActivityViewModel
import com.example.notedown.viewmodel.NoteActivityViewModelFactory

class MainActivity : AppCompatActivity() {


    lateinit var noteActivityViewModel: NoteActivityViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        try {
            setContentView(binding.root)
            val noteRepository=NoteRepository(NoteDatabase(this))
            val noteActivityViewModelFactory= NoteActivityViewModelFactory(noteRepository)
            noteActivityViewModel= ViewModelProvider(this,
                noteActivityViewModelFactory)[NoteActivityViewModel::class.java]


        }catch (e: Exception)
        {
            Log.d("TAG", "ERROR")
        }
    }
}