package com.example.github_admin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_admin.model.DetailUser
import com.example.github_admin.model.repository.GithubAdminRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(private val repository: GithubAdminRepository): ViewModel() {

    private val _userData = MutableStateFlow<DetailUser?>(null)
    val userData: StateFlow<DetailUser?> = _userData

    fun getDetailUser(userLogin: String?) {
        _userData.value = null
        if (userLogin != null) {
            viewModelScope.launch(Dispatchers.IO) {
                val user = repository.getDetailUser(userLogin)
                _userData.value = user
            }
        }
    }
}