package com.example.flowandroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.flowandroom.databinding.ActivityMainBinding
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin


class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Koin の初期化
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(appModule)
        }

        val binding : ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        viewModel.users.observe(this, Observer {
            binding.mainText.text = it.toString()
        })

        viewModel.usersSortedByFirstName.observe(this, Observer {
            binding.sortFirstNameText.text = it.toString()
        })

        viewModel.usersSortedByLastName.observe(this, Observer {
            binding.sortLastNameText.text = it.toString()
        })

        viewModel.usersSortedByAge.observe(this, Observer {
            binding.sortAgeText.text = it.toString()
        })
    }
}