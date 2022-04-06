package com.kusch.pagging.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kusch.pagging.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val passengersViewModel by viewModel<PostViewModel>()
    lateinit var postAdapter: PostAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupList()
        setupView()
    }

    private fun setupList() {
        postAdapter = PostAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postAdapter.withLoadStateHeaderAndFooter(
                header = PostLoadStateAdapter { postAdapter.retry() },
                footer = PostLoadStateAdapter { postAdapter.retry() }
            )
            setHasFixedSize(true)
        }

    }

    private fun setupView() {
        lifecycleScope.launch {
            passengersViewModel.posts.collectLatest { pagedData ->
                postAdapter.submitData(pagedData)
            }
        }
    }
}