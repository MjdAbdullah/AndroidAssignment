package com.example.recipeapp

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
import com.example.recipeapp.Data.Recipes

class Update_Fragment : Fragment() {

    lateinit var bUpdate : Button
    lateinit var etTitleUpdate: EditText
    lateinit var etAuthorUpdate: EditText
    lateinit var etIngredientsUpdate: EditText
    lateinit var etInstructionsUpdate: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_, container, false)

        val SP = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val myViewRecipes = ViewModelProvider(this).get(myViewModel::class.java)

        bUpdate = view.findViewById(R.id.bUpdate)

        etTitleUpdate = view.findViewById(R.id.etTitleUpdate)
        etTitleUpdate.setText(SP.getString("T",""))

        etAuthorUpdate = view.findViewById(R.id.etAuthorUpdate)
        etAuthorUpdate.setText(SP.getString("A",""))

        etIngredientsUpdate = view.findViewById(R.id.etIngredientsUpdate)
        etIngredientsUpdate.setText(SP.getString("Ing",""))

        etInstructionsUpdate = view.findViewById(R.id.etInstructionsUpdate)
        etInstructionsUpdate.setText(SP.getString("Ins",""))

        bUpdate.setOnClickListener {
            if(etTitleUpdate.text.isNotEmpty() && etAuthorUpdate.text.isNotEmpty()
                && etIngredientsUpdate.text.isNotEmpty() && etInstructionsUpdate.text.isNotEmpty()){
                var ID = SP.getInt("ID",0)
                val newUpdate = Recipes(ID, etTitleUpdate.text.toString(),etAuthorUpdate.text.toString(),
                    etIngredientsUpdate.text.toString(),etInstructionsUpdate.text.toString())
                myViewRecipes.updateRecipe(newUpdate)
                findNavController().navigate(R.id.action_update_Fragment_to_main_Fragment)
            }
        }

        return view
    }

}