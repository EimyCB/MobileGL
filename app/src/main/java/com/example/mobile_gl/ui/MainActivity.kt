package com.example.mobile_gl.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gl.data.Resource
import com.example.mobile_gl.data.model.MainResponse
import com.example.mobile_gl.databinding.ActivityMainBinding
import com.example.mobile_gl.ui.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private lateinit var list: List<MainResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.onStart()
        mainViewModel.listResult.observe(this, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    if (it.data != null) list = it.data
                    initRecyclerView()
                    binding.loading.visibility = View.VISIBLE
                }
                Resource.Status.LOADING -> binding.loading.visibility = View.VISIBLE
                Resource.Status.ERROR -> {
                    binding.loading.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvMainList.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(list)
        binding.rvMainList.adapter = adapter
    }
}
