package com.example.sampleapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapp.databinding.FragmentHomeBinding
import com.example.sampleapp.models.TopHeadLines
import com.example.sampleapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var newsArticlesAdapter: NewsArticlesAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoadingListener()
        homeViewModel.fetchTopHeadlines()
    }

    private fun setupLoadingListener() {
        homeViewModel.showProgress.observe(viewLifecycleOwner) {
            binding.progressIndicator.visibility = if (it) View.VISIBLE else View.GONE
        }
        homeViewModel.headlinesLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.progressIndicator.visibility = View.VISIBLE
            } else {
                setupDataForRecyclerView(it)
            }
        }
        homeViewModel.isErrorOccurred.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error occurred, Try again later", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupDataForRecyclerView(topHeadLines: TopHeadLines) {
        newsArticlesAdapter = NewsArticlesAdapter(topHeadLines)
        binding.recyclerView.apply {
            visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(this.context)
            adapter = newsArticlesAdapter
        }
    }
}