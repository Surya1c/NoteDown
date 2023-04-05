package com.example.notedown.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notedown.R
import com.example.notedown.activities.MainActivity
import com.example.notedown.databinding.BottomSheetLayoutBinding
import com.example.notedown.databinding.FragmentSaveOrUpdatefragmentBinding
import com.example.notedown.model.Note
import com.example.notedown.utils.hideKeyboard
import com.example.notedown.viewmodel.NoteActivityViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.card.MaterialCardView
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*


class SaveOrUpdatefragment : Fragment(R.layout.fragment_save_or_updatefragment) {

    private lateinit var navController: NavController
    private lateinit var contextBinding: FragmentSaveOrUpdatefragmentBinding
    private var note: Note? = null
    private var color = -1
    private val noteActivityViewModel: NoteActivityViewModel by activityViewModels()
    private val currentDate = SimpleDateFormat.getInstance().format(Date())
    private val job = CoroutineScope(Dispatchers.Main)
    private val args: SaveOrUpdatefragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragment
            scrimColor = Color.TRANSPARENT
            duration = 300L
        }
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contextBinding= FragmentSaveOrUpdatefragmentBinding.bind(view)


        navController= Navigation.findNavController(view)
        val activity = activity as MainActivity

        contextBinding.backBtn.setOnClickListener {
            requireView().hideKeyboard()
            navController.popBackStack()
        }

        contextBinding.saveNote.setOnClickListener{
            saveNote()

            try {
                contextBinding.etNoteContent.setOnFocusChangeListener{_, hasFocus->
                    if(hasFocus){
                        contextBinding.bottomBar.visibility= View.VISIBLE
                        contextBinding.etNoteContent.setStylesBar((contextBinding.styleBar))
                    }
                    else contextBinding.bottomBar.visibility= View.GONE
                }


            }catch (e: Throwable)
            {
                Log.d("TAG", e.stackTraceToString())
            }



            contextBinding.fabColorPick.setOnClickListener {
                val bottomSheetDialog=BottomSheetDialog(
                    requireContext(),
                    R.style.BottomSheetDialogTheme
                )
                val bottomSheetView: View=layoutInflater.inflate(


                R.layout.bottom_sheet_layout,null,

                )
                with(bottomSheetDialog){
                    setContentView(bottomSheetView)
                    show()
                }
                val bottomSheetBinding= BottomSheetLayoutBinding.bind(bottomSheetView)
                bottomSheetBinding.apply {
                    colorPicker.apply {
                        setSelectedColor(color)
                        setOnColorSelectedListener {
                            value->
                            color = value
                            contextBinding.apply {
                                noteContentFragmentParent.setBackgroundColor(color)
                                toolbarFragmentNoteContent.setBackgroundColor(color)
                                bottomBar.setBackgroundColor(color)
                                activity.window.statusBarColor=color
                            }
                            bottomSheetBinding.bottomSheetParent.setCardBackgroundColor(color)

                        }

                    }
                    bottomSheetParent.setCardForegroundColor(color)


                }
                bottomSheetView.post {
                    bottomSheetDialog.behavior.state=BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
    }


    private fun saveNote() {
        if (contextBinding.etNoteContent.text.toString().isEmpty() ||
                contextBinding.eTitle.text.toString().isEmpty())
        {
            Toast.makeText(activity,"Something is Empty",Toast.LENGTH_SHORT).show()
        }
        else
        {
            note=args.note
            when(note){
                null-> {noteActivityViewModel.saveNote(
                    Note(
                        0,
                        contextBinding.eTitle.text.toString(),
                        contextBinding.etNoteContent.getMD().toString(),
                        currentDate,
                        color
                    )
                )
                navController.navigate(SaveOrUpdatefragmentDirections.actionSaveOrUpdatefragmentToNotefragment())

            }
            else->{
            //update note

            }




        }
    }

}

private fun MaterialCardView.setCardForegroundColor(color: Int) {
    TODO("Not yet implemented")}
}





