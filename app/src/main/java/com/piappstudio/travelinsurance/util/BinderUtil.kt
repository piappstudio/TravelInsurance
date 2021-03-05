package com.piappstudio.travelinsurance.util

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.common.TIApplication

object BinderUtil {
    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setError(textInputLayout: TextInputLayout, error:Int) {
        if (error== R.string.empty) {
            textInputLayout.error = null
        } else {
            textInputLayout.error = TIApplication.INSTANCE?.getString(error)
        }
    }
    val PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".toRegex()
}