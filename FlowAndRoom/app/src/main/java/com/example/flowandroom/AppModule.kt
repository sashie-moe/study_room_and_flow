package com.example.flowandroom

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single {
        Room.databaseBuilder(androidContext(), Database::class.java, "users").build()
    }

    single {
        get<Database>().userDao()
    }

    single {
        UserRepository(get())
    }

    viewModel {
        MainViewModel(get())
    }
}