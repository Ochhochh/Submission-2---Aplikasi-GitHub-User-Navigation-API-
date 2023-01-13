package com.example.submission2_mygithubapp.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2_mygithubapp.Api.User
import com.example.submission2_mygithubapp.Adapter.ListUserAdapter
import com.example.submission2_mygithubapp.Model.MainViewModel
import com.example.submission2_mygithubapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ListUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        supportActionBar?.hide()

        listDisplay()

        //SearchUser
        binding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { view, id, _ ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                val textSearch = view.text.toString()
                viewModel.getSearchUser(textSearch)

                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })

    }

    private fun listDisplay() {
        viewModel.User.observe(this) { user ->
            adapter = ListUserAdapter(user)
            binding.rvUser.adapter = adapter
            binding.rvUser.layoutManager = LinearLayoutManager(this)
            adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: User) {
                    Intent(this@MainActivity, DetailActivity::class.java).also {
                        it.putExtra(DetailActivity.EXTRA_DETAIL, data.login)
                        startActivity(it)
                    }
                }
            })
        }
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}