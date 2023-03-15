package com.example.nyc_school_challenges.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.nyc_school_challenges.ui.ScoreFragment
import com.example.nyc_school_challenges.databinding.LayoutSearchItemBinding
import com.example.nyc_school_challenges.domain.SchoolModel
import com.example.nyc_school_challenges.ui.home.SchoolViewModel
import kotlinx.coroutines.selects.select

class SearchItemAdapter(var schools : List<SchoolModel>,val select: (SchoolModel) -> Unit) : RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val binding = LayoutSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return schools.size
    }

    fun changeData(newData : List<SchoolModel>){
        schools = newData
        notifyDataSetChanged()
    }

    inner class SearchItemViewHolder(private val binding: LayoutSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvSchoolName.text = schools[position].schoolName
            binding.tvSchoolAddress.text = schools[position].addressString
            binding.clTop.setOnClickListener{
                //will launch a dialog fragment by our context
                select(schools[position])

            }
        }
    }
}