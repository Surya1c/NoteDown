package com.example.notedown.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.notedown.R
import com.example.notedown.activities.MainActivity
import com.example.notedown.databinding.FragmentNotefragmentBinding
import com.example.notedown.utils.hideKeyboard
import com.example.notedown.viewmodel.NoteActivityViewModel
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Notefragment : Fragment(R.layout.fragment_notefragment) {


    private lateinit var noteBinding: FragmentNotefragmentBinding
    private val noteActivityViewModel: NoteActivityViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        exitTransition=MaterialElevationScale(false).apply {
            duration=350
        }
        enterTransition=MaterialElevationScale(true).apply {
            duration=350
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteBinding= FragmentNotefragmentBinding.bind(view)
        val activity=activity as MainActivity
        val navController= Navigation.findNavController(view)
        requireView().hideKeyboard()
        CoroutineScope(Dispatchers.Main).launch {
            delay(10)
            activity.window.statusBarColor= Color.GRAY
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.statusBarColor= Color.parseColor("#9E9D9D")

        }

        noteBinding.addNoteFab.setOnClickListener {
            noteBinding.appBarLayout.visibility= View.INVISIBLE
            navController.navigate(NotefragmentDirections.actionNotefragmentToSaveOrUpdatefragment())
        }
        noteBinding.innerFab.setOnClickListener {
            noteBinding.appBarLayout.visibility= View.INVISIBLE
            navController.navigate(NotefragmentDirections.actionNotefragmentToSaveOrUpdatefragment())
        }




    }
}