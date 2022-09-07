package com.arash.altafi.samplepayload.payload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.arash.altafi.samplepayload.MainViewModel
import com.arash.altafi.samplepayload.databinding.ActivityRecyclerViewWithPayloadBinding

class RecyclerViewWithPayloadActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRecyclerViewWithPayloadBinding.inflate(layoutInflater)
    }

    private val payloadAdapter = AdapterWithPayload(this::toggleFavoriteStatus)

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        lifecycleScope.launchWhenResumed {
            viewModel.itemList.collect { itemList ->
                payloadAdapter.submitList(itemList)
            }
        }

        binding.rcWithPayload.apply {
            adapter = payloadAdapter
            setHasFixedSize(true)
        }
    }

    private fun toggleFavoriteStatus(id: String, isFavorite: Boolean) {
        viewModel.toggleFavoriteStatus(id, isFavorite)
    }

}