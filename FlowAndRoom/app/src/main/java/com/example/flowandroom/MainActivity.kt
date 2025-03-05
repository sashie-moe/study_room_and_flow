package com.example.flowandroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.databinding.DataBindingUtil
import com.example.flowandroom.databinding.ActivityMainBinding
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin


class MainActivity : AppCompatActivity() {
    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Koin の初期化
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(appModule)
        }

        val binding : ActivityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main).apply {
                composeView.setContent {
                    // You're in Compose world!
                    MaterialTheme {
                        ShowAllData(mainViewModel)
                    }
                }
            }


//        viewModel.users.observe(this, Observer {
//            binding.mainText.text = it.toString()
//        })
//
//        viewModel.usersSortedByFirstName.observe(this, Observer {
//            binding.sortFirstNameText.text = it.toString()
//        })
//
//        viewModel.usersSortedByLastName.observe(this, Observer {
//            binding.sortLastNameText.text = it.toString()
//        })
//
//        viewModel.usersSortedByAge.observe(this, Observer {
//            binding.sortAgeText.text = it.toString()
//        })
    }
}

@Composable
fun ShowAllData(viewmodel:MainViewModel){
    val allData by viewmodel.users.observeAsState()
    Text(allData.toString())
}