package com.example.roomwithretrofit.feature.user.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomwithretrofit.core.networkUtils.ResultWrapper
import com.example.roomwithretrofit.feature.user.data.repository.UserRepository
import com.example.roomwithretrofit.feature.user.ui.UserFragmentState
import com.example.roomwithretrofit.feature.user.ui.model.UserView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _users = MutableLiveData<List<UserView>?>()
    val users = _users

    private val _fragmentState = MutableLiveData(UserFragmentState.INITIAL_STATE)
    val fragmentState = _fragmentState

    private val _fragmentStateMessage = MutableLiveData<String>()
    val fragmentStateMessage = _fragmentStateMessage

    init {
        fetchUers()
    }

    fun fetchUers() {
        viewModelScope.launch(Dispatchers.IO) {
            val responseUsers = userRepository.getUsers()

            withContext(Dispatchers.Main) {
                when (responseUsers) {
                    is ResultWrapper.AppError -> {
                        _fragmentStateMessage.value = responseUsers.appMessage
                        _fragmentState.value = UserFragmentState.APPERROR
                        _users.value = responseUsers.data
                    }
                    is ResultWrapper.Failure -> {
                        _fragmentStateMessage.value =
                            "${responseUsers.severMessage} ${responseUsers.code}"
                        _fragmentState.value = UserFragmentState.FAILURE
                        _users.value = responseUsers.data
                    }
                    is ResultWrapper.Success -> {
                        _users.value = responseUsers.data
                        _fragmentState.value = UserFragmentState.SUCCESS

                    }
                }
            }
        }
    }
}

