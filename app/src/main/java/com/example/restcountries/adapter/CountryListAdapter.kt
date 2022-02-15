package com.example.restcountries.adapter

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountries.R
import com.example.restcountries.databinding.CountryListRowBinding
import com.example.restcountries.model.CountryModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import java.text.NumberFormat
import java.util.*

class CountryListAdapter(val activity: Activity): RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>() {

    private var countryList: List<CountryModel>? = null

    fun setCountryList(countryList: List<CountryModel>){
        this.countryList = countryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val view = CountryListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        holder.bind(countryList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
       if (countryList == null) return 0
       else return countryList?.size!!
    }

    class CountryListViewHolder(view: CountryListRowBinding): RecyclerView.ViewHolder(view.root) {

        private val flagImage = view.ivFlag
        private val tvCountryName = view.tvCountryName
        private val tvCountryCapital = view.tvCapital
        private val tvRegion  = view.tvRegion
        private val tvPopulation = view.tvPopulation

        fun bind(data: CountryModel, activity: Activity){
            tvCountryName.text = "${data.name} (${data.alpha2Code})"
            tvCountryCapital.text = "Capital: " +data.capital
            tvRegion.text = "Region: "+data.region
            tvPopulation.text = "Population: ${NumberFormat.getNumberInstance(Locale.CANADA).format(data.population)}"

            GlideToVectorYou.justLoadImage(activity, Uri.parse(data.flag), flagImage)
        }
    }
}