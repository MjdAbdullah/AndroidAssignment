package com.example.recipeapp

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

class Add_Fragment : Fragment() {

    lateinit var bAdd : Button
    lateinit var etTitle: EditText
    lateinit var etAuthor: EditText
    lateinit var etIngredients: EditText
    lateinit var etInstructions: EditText
    val myViewRecipes by lazy { ViewModelProvider(this).get(myViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_, container, false)

        bAdd = view.findViewById(R.id.bAdd)
        etTitle = view.findViewById(R.id.etTitle)
        etAuthor = view.findViewById(R.id.etAuthor)
        etIngredients = view.findViewById(R.id.etIngredients)
        etInstructions = view.findViewById(R.id.etInstructions)

        bAdd.setOnClickListener {
            if(etTitle.text.isNotEmpty() && etAuthor.text.isNotEmpty() && etIngredients.text.isNotEmpty()
                && etInstructions.text.isNotEmpty()){
                    val new = Recipes(0,etTitle.text.toString(), etAuthor.text.toString(),
                        etIngredients.text.toString(), etInstructions.text.toString())
                myViewRecipes.addRecipe(new)
                findNavController().navigate(R.id.action_add_Fragment_to_main_Fragment)
            }
        }


        return view
    }

}