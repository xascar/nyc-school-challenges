package com.example.nyc_school_challenges.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nyc_school_challenges.R
import com.example.nyc_school_challenges.ui.adapter.SearchItemAdapter
import com.example.nyc_school_challenges.databinding.ActivityMainBinding
import com.example.nyc_school_challenges.viewmodel.SchoolViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var menu : Menu
    private lateinit var searchItemAdapter: SearchItemAdapter
    private lateinit var viewModel : SchoolViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set content with activity_main
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
        searchItemAdapter = SearchItemAdapter(this, viewModel, listOf())
        binding.rvSearch.adapter = searchItemAdapter
        binding.rvSearch.layoutManager = LinearLayoutManager(this)


        val results = viewModel.lSearchResult.value
        searchItemAdapter.changeData(results)
        setContentView(binding.root)
    }

    override fun onStart() {
        lifecycleScope.launch{
            viewModel.lSearchResult.collect{
                searchItemAdapter.changeData(it)
            }
        }
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        this.menu = menu

        val search = menu.findItem(R.id.search)

        val searchView = search.actionView as SearchView
        //add search functionalities
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    viewModel.search(query ?: "")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch {
                    viewModel.search(newText ?: "")
                }
                return true
            }
        })

        return true
    }


}

