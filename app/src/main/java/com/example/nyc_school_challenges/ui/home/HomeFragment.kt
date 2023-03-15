package com.example.nyc_school_challenges.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nyc_school_challenges.databinding.FragmentHomeBinding
import com.example.nyc_school_challenges.ui.ScoreFragment
import com.example.nyc_school_challenges.ui.adapter.SearchItemAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: SchoolViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        val searchItemAdapter = SearchItemAdapter(listOf()){
            viewModel.select(it)
            ScoreFragment.newInstance().show(requireActivity().supportFragmentManager, "SchoolDetailDialogFragment")

        }
        binding.rvSearch.adapter = searchItemAdapter
        binding.rvSearch.layoutManager = LinearLayoutManager(context)


        lifecycleScope.launchWhenStarted {
            viewModel.lSearchResult.collect { value ->
                searchItemAdapter.changeData(value)
            }
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}