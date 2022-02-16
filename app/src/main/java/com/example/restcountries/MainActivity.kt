package com.example.restcountries

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restcountries.adapter.CountryListAdapter
import com.example.restcountries.databinding.ActivityMainBinding
import com.example.restcountries.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var countryListAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView(){
        binding.countryListRecyclerView.layoutManager = LinearLayoutManager(this)
        countryListAdapter = CountryListAdapter(this)
        binding.countryListRecyclerView.adapter = countryListAdapter

        val decoration = DividerItemDecoration(binding.countryListRecyclerView.context, (binding.countryListRecyclerView.layoutManager as LinearLayoutManager).orientation)
        binding.countryListRecyclerView.addItemDecoration(decoration)
    }

    private fun initViewModel(){
        val viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null){
                countryListAdapter.setCountryList(it)
                countryListAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Data fetched", Toast.LENGTH_SHORT).show()
                Log.d("MY_TAG", "initViewModel: list: $it")
            }
            else{
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
                Log.e("MY_TAG", "initViewModel: list = null")
            }
        })

        viewModel.makeApiCall2()
    }
}