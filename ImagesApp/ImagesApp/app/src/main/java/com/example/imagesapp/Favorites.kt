package com.example.imagesapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesapp.RVAdapter.RVAdapterFavorites

class Favorites : Fragment() {

    val myViewFav by lazy { ViewModelProvider(this).get(myViewModel::class.java) }
    lateinit var RV: RVAdapterFavorites
    lateinit var rvFav: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        rvFav = view.findViewById(R.id.rvFav)
        RV = RVAdapterFavorites(this)

        myViewFav.getImages().observe(viewLifecycleOwner,{
            ImagesFav -> RV.update(ImagesFav)
        })

        rvFav.adapter = RV
        rvFav.layoutManager = LinearLayoutManager(requireContext())

        setHasOptionsMenu(true)

        return view
    }
    //----------------------------------------------------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_menu,menu)
        val item: MenuItem = menu!!.getItem(1)
        item.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.List ->{
                findNavController().navigate(R.id.action_favorites_to_imges)
            }
        }
        return true
    }

}