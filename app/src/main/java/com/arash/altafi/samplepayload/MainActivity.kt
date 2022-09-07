package com.arash.altafi.samplepayload

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.samplepayload.databinding.ActivityMainBinding
import com.arash.altafi.samplepayload.payload.RecyclerViewWithPayloadActivity
import com.arash.altafi.samplepayload.withoutPayload.RecyclerViewWithoutPayloadActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.apply {
            btnRecyclerViewWithPayload.setOnClickListener {
                startActivity(Intent(this@MainActivity, RecyclerViewWithPayloadActivity::class.java))
            }
            btnRecyclerViewWithoutPayload.setOnClickListener {
                startActivity(Intent(this@MainActivity, RecyclerViewWithoutPayloadActivity::class.java))
            }
        }
    }

}