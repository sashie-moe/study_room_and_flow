package com.example.flowandroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: UserRepository
) : ViewModel() {
    val users: LiveData<List<User>> = repo.getUsers().asLiveData()
    val usersSortedByFirstName: LiveData<List<User>> = repo.getUserSortedByFirstName().asLiveData()
    val usersSortedByLastName: LiveData<List<User>> = repo.getUserSortedByLastName().asLiveData()
    val usersSortedByAge: LiveData<List<User>> = repo.getUserSortedByAge().asLiveData()

    // サンプルデータを書き込む処理
    // 必要に応じて追加・削除をする
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.tryUpdateRecentUsersCache()
        }
    }
}