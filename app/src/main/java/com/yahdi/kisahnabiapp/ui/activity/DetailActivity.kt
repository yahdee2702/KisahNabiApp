package com.yahdi.kisahnabiapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.yahdi.kisahnabiapp.R
import com.yahdi.kisahnabiapp.data.model.KisahResponse
import com.yahdi.kisahnabiapp.databinding.ActivityDetailBinding
import com.yahdi.kisahnabiapp.utils.Utils

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    companion object {
        const val KISAH_DATA = "kisah_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        val data = intent.getParcelableExtra<KisahResponse>(KISAH_DATA)

        data?.let {
            binding.apply {
                collapsingToolbar.title = getString(R.string.txt_kisah_hero, it.name)
                tvHeroName.text = it.name
                tvHeroContent.text = it.content
                Glide.with(root.context)
                    .load(Utils.fromHttpToHttpsString(data.thumb))
                    .apply(RequestOptions())
                    .override(500, 500)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .placeholder(R.drawable.img_not_found)
                    .into(ivHeroThumb)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}