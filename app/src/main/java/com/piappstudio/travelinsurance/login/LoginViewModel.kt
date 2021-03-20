package com.piappstudio.travelinsurance.login

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.model.mbo.User
import com.piappstudio.travelinsurance.model.repository.TravelRepository
import com.piappstudio.travelinsurance.util.Resource
import com.piappstudio.travelinsurance.util.toSHA256Hash
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: TravelRepository): ViewModel() {

    private  val _liveUser = MutableLiveData<User>(User())
    val liveUser:LiveData<User> = _liveUser
    private val _errorUser:MutableLiveData<Int> = MutableLiveData(R.string.empty)
    val errorUser:LiveData<Int> = _errorUser
    private val _errorPass:MutableLiveData<Int> = MutableLiveData(R.string.empty)
    val errorPass:LiveData<Int> = _errorPass
    val isFingerPrintEnabled = ObservableField<Boolean>(true)
    private  val _isLoginSuccessful:MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoginSuccessful:LiveData<Boolean> = _isLoginSuccessful

    val isShowProgressBar = MutableLiveData<Boolean>(false)
    fun resetLoginSuccessful(result:Boolean) {
        _isLoginSuccessful.postValue(result)
    }
    fun onClickLogin():Boolean {
        var isValid = true
        if (liveUser.value?.userName.isNullOrEmpty()) {
            _errorUser.postValue(R.string.msg_error_user_name)
            isValid = false
        } else {
            _errorUser.postValue(R.string.empty)
        }

        val PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".toRegex()
        if (liveUser.value?.password.isNullOrEmpty()) {
            _errorPass.postValue(R.string.msg_error_password)
            isValid = false
        } else if(!PATTERN.matches(liveUser.value?.password!!)){
           _errorPass.postValue(R.string.msg_error_password_regex)
            isValid = false
        } else {
            _errorPass.postValue(R.string.empty)
        }

        if (isValid) {
            viewModelScope.launch {
                isShowProgressBar.postValue(true)
                val user = repository.findByUserNamePassword(liveUser.value!!.userName,
                        liveUser.value!!.password.toSHA256Hash())
                isShowProgressBar.postValue(false)
                if (user?.uid == null) {
                    _errorPass.postValue(R.string.msg_error_invalid_username_password)
                } else {
                    _isLoginSuccessful.postValue(true)
                }
            }
        }

        return isValid
    }
}