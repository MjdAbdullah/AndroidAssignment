package com.example.noteapp_fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class UpdateNote : Fragment() {
    lateinit var etUpdateText : EditText
    lateinit var bUpdateText : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update_note, container, false)

        val SP = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val MyVM = ViewModelProvider(this).get(MyVM::class.java)

        etUpdateText = view.findViewById(R.id.etUpdateText)
        bUpdateText = view.findViewById(R.id.bUpdateText)

        bUpdateText.setOnClickListener {
            if (etUpdateText.text.isNotEmpty()){
                var Id = SP.getInt("NoteId",0)
                MyVM.editNote(Id,etUpdateText.text.toString())
                findNavController().navigate(R.id.action_updateNote_to_home2)
            }
        }
        return view

    }

}