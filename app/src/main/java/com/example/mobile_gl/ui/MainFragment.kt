package com.example.mobile_gl.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gl.R
import com.example.mobile_gl.data.Resource
import com.example.mobile_gl.data.model.MainResponse
import com.example.mobile_gl.databinding.FragmentMainBinding
import com.example.mobile_gl.ui.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private lateinit var list: List<MainResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        mainViewModel.onStart()
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        mainViewModel.listResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.loading.visibility = View.GONE
                    binding.error.visibility = View.GONE
                    if (it.data != null) list = it.data
                    initRecyclerView()
                }
                Resource.Status.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    binding.loading.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                    Toast.makeText(context, getString(R.string.Error), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvMainList.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(list, onClick = { list ->
            navigateToDetail(list)
        })
        binding.rvMainList.adapter = adapter
    }

    private fun navigateToDetail(detail: MainResponse) {
        val data = Bundle()
        data.putSerializable(DetailFragment.DATA_CARD, detail)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment, data)
    }
}
