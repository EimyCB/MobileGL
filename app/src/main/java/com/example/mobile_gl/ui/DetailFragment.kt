package com.example.mobile_gl.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mobile_gl.R
import com.example.mobile_gl.data.model.MainResponse
import com.example.mobile_gl.databinding.FragmentDetailBinding
import com.example.mobile_gl.databinding.FragmentMainBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: Fragment() {
    private lateinit var binding: FragmentDetailBinding

    companion object{
        const val DATA_CARD = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val data = requireArguments().getSerializable(DATA_CARD) as MainResponse
                Picasso.get().load(data.image).into(binding.ivImage)
                binding.tvTitle.text = data.title
                binding.tvDescription.text = data.description
        }else {
            Toast.makeText(context, getString(R.id.error), Toast.LENGTH_SHORT).show()
        }

    }
}