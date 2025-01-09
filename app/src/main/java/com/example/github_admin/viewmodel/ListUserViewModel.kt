package com.example.github_admin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_admin.model.User
import com.example.github_admin.model.repository.GithubAdminRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(private val repository: GithubAdminRepository) :
    ViewModel() {

    private val _listUser = MutableStateFlow<List<User>>(emptyList())
    val listUser: StateFlow<List<User>> = _listUser

    private var currentPage = 0

    init {
        getListUser()
    }

    fun getListUser() {
        viewModelScope.launch {
            try {
                val newUsers = repository.getListUser(perPage = 20, since = currentPage) ?: emptyList()
                if (newUsers.isNotEmpty()) {
                    _listUser.value += newUsers
                    currentPage = newUsers.last().id
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}