package com.example.noteapp_fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Home : Fragment() {

    lateinit var rvMain : RecyclerView
    lateinit var RV : RVAdapter
    lateinit var bAdd : Button
    lateinit var etNote : EditText
    val myVM by lazy { ViewModelProvider(this).get(MyVM::class.java) }
    private lateinit var notes : List<Note>

    lateinit var SP : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        SP = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        notes = arrayListOf()
        myVM.getNotes().observe(viewLifecycleOwner, {
                notes -> RV.update(notes)
        })

        bAdd = view.findViewById(R.id.bAdd)
        etNote = view.findViewById(R.id.etNote)
        rvMain = view.findViewById(R.id.rvMain)

        bAdd.setOnClickListener {
            if (etNote.text.isNotEmpty()){
                myVM.addNote(etNote.text.toString())
                etNote.text.clear()
                etNote.clearFocus()
            }
        }
        myVM.getNotes()

        RV = RVAdapter(this)
        rvMain.adapter = RV
        rvMain.layoutManager = LinearLayoutManager(requireContext())

        myVM.getNotes()

        return view
    }

}