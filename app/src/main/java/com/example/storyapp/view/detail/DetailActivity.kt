package com.example.storyapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.storyapp.R
import com.example.storyapp.data.remote.response.ListStoryItem
import com.example.storyapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.detail_story)

        val story = intent.getParcelableExtra<ListStoryItem>(EXTRA_DATA)

        binding.apply {
            binding.tvName.text = story?.name
            binding.tvDescription.text = story?.description

            Glide
                .with(this@DetailActivity)
                .load(story?.photoUrl)
                .fitCenter()
                .into(binding.ivProfilePhoto)
        }
    }

    companion object {
        const val EXTRA_DATA = "story"
    }
}