package com.example.imagesapp

import android.os.Bundle
import android.view.*
import android.view.View.inflate
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesapp.Data.ImagesDetails
import com.example.imagesapp.RVAdapter.RVAdapterImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class Images : Fragment() {

    lateinit var rvImages: RecyclerView
    lateinit var RV: RVAdapterImage
    val KEY = "c852522d84e1390193d4d866171486b4"
    lateinit var etSearch: EditText
    lateinit var bSearch: Button
    var ImagesList = ArrayList<ImagesDetails>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_imges, container, false)

        etSearch = view.findViewById(R.id.etSearch)
        bSearch = view.findViewById(R.id.bSearch)
        rvImages = view.findViewById(R.id.rvImages)

        bSearch.setOnClickListener {
            if(etSearch.text.isNotEmpty()){
                CallApi(etSearch.text.toString())
            }
        }

        RV = RVAdapterImage(this, ImagesList)
        rvImages.adapter = RV
        rvImages.layoutManager = LinearLayoutManager(requireContext())

        setHasOptionsMenu(true)

        return view
    }
    //---------------------------------------------------------------------------------------------
    // menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_menu,menu)
        val item: MenuItem = menu!!.getItem(0)
        item.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.Favorites ->{
                findNavController().navigate(R.id.action_imges_to_favorites)
            }
        }
        return true
    }
    //---------------------------------------------------------------------------------------------
    private fun CallApi(UserInput: String) {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val api = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&per_page=10&api_key=${KEY}&tags=${UserInput}&format=json&nojsoncallback=1")
                    .readText(Charsets.UTF_8)
                if (api.isNotEmpty()){
                    getData(api)
                }
            }catch (e: Exception){
                println("Error $e")
            }
        }
    }
    //---------------------------------------------------------------------------------------------
    // read the json and send the array to RecyclerViewAdapter
    private suspend fun getData(api: String) {
        ImagesList.clear()
        withContext(Dispatchers.Main){
            val photos = JSONObject(api).getJSONObject("photos").getJSONArray("photo")
            for (i in 0 until photos.length()){
                // for read the json
                val id = photos.getJSONObject(i).getString("id")
                val secret = photos.getJSONObject(i).getString("secret")
                val server = photos.getJSONObject(i).getString("server")
                val farm = photos.getJSONObject(i).getInt("farm")
                val title = photos.getJSONObject(i).getString("title")
                // add the data to array image
                ImagesList.add(ImagesDetails(0,title,"https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg"))
            }
            // show the Recycler View
            RV.update(ImagesList)
        }
    }

}