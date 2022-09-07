package com.arash.altafi.samplepayload.withoutPayload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.arash.altafi.samplepayload.MainViewModel
import com.arash.altafi.samplepayload.databinding.ActivityRecyclerViewWithoutPayloadBinding

class RecyclerViewWithoutPayloadActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRecyclerViewWithoutPayloadBinding.inflate(layoutInflater)
    }

    private val withoutPayloadAdapter = AdapterWithoutPayload(this::toggleFavoriteStatus)

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
                withoutPayloadAdapter.submitList(itemList)
            }
        }

        binding.rcWithoutPayload.apply {
            adapter = withoutPayloadAdapter
            setHasFixedSize(true)
        }
    }

    private fun toggleFavoriteStatus(id: String, isFavorite: Boolean) {
        viewModel.toggleFavoriteStatus(id, isFavorite)
    }

}