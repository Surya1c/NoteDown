package com.example.notedown.model

import android.security.identity.AccessControlProfileId
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(

    @PrimaryKey(autoGenerate = true)

    var id: Int=0,
    val title: String,
    val content: String,
    val date: String,
    val color: Int= -1,



) : Serializable

