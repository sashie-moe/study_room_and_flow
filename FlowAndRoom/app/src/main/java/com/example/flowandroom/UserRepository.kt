package com.example.flowandroom

import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDao) {
    fun getUsers() = userDao.getAll()

    fun getUserSortedByFirstName() = getUsers().map {
            it -> it.sortedBy { it.firstName }
    }

    fun getUserSortedByLastName() = getUsers().map {
            it -> it.sortedBy { it.lastName }
    }

    fun getUserSortedByAge() = getUsers().map {
            it -> it.sortedBy { it.age }
    }

    // サンプルデータを生成するための関数（ふつう不要）
    fun tryUpdateRecentUsersCache() {
        userDao.deleteAll()
        userDao.insert(User(1, "A", "G", 10))
        userDao.insert(User(2, "B", "F", 8))
        userDao.insert(User(3, "C", "E", 20))
        userDao.insert(User(4, "D", "D", 25))
        userDao.insert(User(5, "E", "C", 59))
        userDao.insert(User(6, "F", "B", 9))
        userDao.insert(User(7, "G", "A", 1))
    }
}