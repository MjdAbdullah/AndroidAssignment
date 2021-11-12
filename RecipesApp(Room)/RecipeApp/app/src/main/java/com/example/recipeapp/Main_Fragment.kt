package com.example.recipeapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.Data.Recipes
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Main_Fragment : Fragment() {

    lateinit var rvMain: RecyclerView
    lateinit var fabAdd: FloatingActionButton
    lateinit var RV: RVAdapter
    val myViewRecipes by lazy { ViewModelProvider(this).get(myViewModel::class.java) }
    lateinit var SP : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_, container, false)

        rvMain = view.findViewById(R.id.rvMain)
        fabAdd = view.findViewById(R.id.fabAdd)

        SP = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        RV = RVAdapter(this)
        myViewRecipes.getRecipes().observe(viewLifecycleOwner,{
            Recipes -> RV.update(Recipes)
        })


        rvMain.adapter = RV
        rvMain.layoutManager = LinearLayoutManager(requireContext())

        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_main_Fragment_to_add_Fragment)
        }

        return view
    }

    fun updateFragment(recipe: Recipes){
        val ID = recipe.ID
        val Title = recipe.Title
        val Author = recipe.Author
        val Ingredients = recipe.Ingredients
        val Instructions = recipe.Instructions
        with(SP.edit()){
            putInt("ID",ID)
            putString("T",Title)
            putString("A",Author)
            putString("Ing",Ingredients)
            putString("Ins",Instructions)
            apply()
        }
        findNavController().navigate(R.id.action_main_Fragment_to_update_Fragment)
    }

}