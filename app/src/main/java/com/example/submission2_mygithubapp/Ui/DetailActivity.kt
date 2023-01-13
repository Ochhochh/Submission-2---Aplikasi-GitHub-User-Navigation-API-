package com.example.submission2_mygithubapp.Ui

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submission2_mygithubapp.Model.DetailViewModel
import com.example.submission2_mygithubapp.Model.SectionsPagerAdapter
import com.example.submission2_mygithubapp.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel


    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            com.example.submission2_mygithubapp.R.string.followers,
            com.example.submission2_mygithubapp.R.string.following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        detail()
    }

    private fun detail(){
        detailViewModel.detailUser.observe(this) {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load(it.avatarUrl)
                    .circleCrop()
                    .into(binding.dtlAvatar)
                val actionbar = supportActionBar
                actionbar!!.title= it.login
                dtlFollowers.text = StringBuilder(it.followers).append(" Followers")
                dtlFollowing.text = StringBuilder(it.following).append(" Following")
                dtlRepo.text = StringBuilder(it.publicRepos).append(" Repository")
                if (it.name != null){
                    dtlName.text = it.name
                } else {
                    dtlName.text = it.login
                }



                val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailActivity)
                sectionsPagerAdapter.username = it.login

                viewPager.adapter = sectionsPagerAdapter

                val tabLayout : TabLayout = findViewById(com.example.submission2_mygithubapp.R.id.tab_layout)
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = resources.getString(TAB_TITLES[position])
                }.attach()
            }
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val detailUser = intent.getStringExtra(EXTRA_DETAIL).toString()
        detailViewModel.getDetailUser(detailUser)

    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}