package com.yahdi.kisahnabiapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yahdi.kisahnabiapp.R
import com.yahdi.kisahnabiapp.data.model.KisahResponse
import com.yahdi.kisahnabiapp.data.viewModel.KisahViewModel
import com.yahdi.kisahnabiapp.databinding.ActivityMainBinding
import com.yahdi.kisahnabiapp.ui.adapter.KisahListAdapter

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private val kisahListAdapter by lazy {
        KisahListAdapter()
    }

    private var _kisahVM: KisahViewModel? = null
    private val kisahVM get() = _kisahVM as KisahViewModel

    private var savedData: ArrayList<KisahResponse> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TUrn off Night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        _kisahVM = ViewModelProvider(this)[KisahViewModel::class.java]

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupRecyclerView()
        setupData()
        setupSearch()
    }

    private fun setupSearch() {
        binding.svSearchKisah.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isNotEmpty()) {
                        val searched = savedData.filter {
                            Regex(newText, RegexOption.IGNORE_CASE).containsMatchIn(
                                "Kisah ${it.name}"
                            )
                        }

                        kisahListAdapter.setData(ArrayList(searched))
                    } else {
                        kisahListAdapter.setData(savedData)
                    }
                    return false
                }
            },
        )
    }

    private fun setupRecyclerView() {
        binding.rvKisah.apply {
            adapter = kisahListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupData() {
        kisahVM.getKisah().observe(this) {
            savedData = ArrayList(it)
            kisahListAdapter.setData(savedData)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.opt_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}