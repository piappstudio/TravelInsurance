package com.piappstudio.travelinsurance.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.mbo.User

class LoginViewModel: ViewModel() {

    val TAG = LoginViewModel::class.java.name
    private  val _liveUser = MutableLiveData<User>(User())
    val liveUser:LiveData<User> = _liveUser

    private val _errorUser:MutableLiveData<Int> = MutableLiveData(R.string.empty)
    val errorUser:LiveData<Int> = _errorUser
    private val _errorPass:MutableLiveData<Int> = MutableLiveData(R.string.empty)
    val errorPass:LiveData<Int> = _errorPass
    val isFingerPrintEnabled = ObservableField<Boolean>(true)


    fun onClickLogin():Boolean {
//        Log.d(TAG, "is Checked ${isFingerPrintEnabled.get()}")
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

        return isValid
    }
}