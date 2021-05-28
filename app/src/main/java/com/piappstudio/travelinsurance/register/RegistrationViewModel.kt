/*
 *
 *  * Copyright 2021 All rights are reserved by Pi App Studio
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *
 */

package com.piappstudio.travelinsurance.register

import androidx.lifecycle.*
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.model.mbo.User
import com.piappstudio.travelinsurance.model.mbo.UserError
import com.piappstudio.travelinsurance.model.repository.TravelRepository
import com.piappstudio.travelinsurance.util.BinderUtil
import com.piappstudio.pilibrary.utility.Resource
import com.piappstudio.travelinsurance.common.TIApplication
import com.piappstudio.travelinsurance.util.toSHA256Hash
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RegistrationViewModel (private  val repository: TravelRepository): ViewModel() {

    private val _liveUserData = MutableLiveData<User>(User())
    val liveUser:LiveData<User> = _liveUserData

    private val _liveUserError = MutableLiveData<UserError>(UserError())
    val liveErrorUser:LiveData<UserError> = _liveUserError

    // Registration updates
    val liveRegistrationFlow = MutableLiveData<Resource.Status>(Resource.Status.NONE)

    fun validateForm(confirmPassword:String):Boolean {

        var isValid = true
        if (liveUser.value?.firstName.isNullOrEmpty()) {
            _liveUserError.postValue(UserError(firstNameError =  R.string.enter_first_name))
            isValid = false
        }
        if(isValid && liveUser.value?.lastName.isNullOrEmpty()) {
            _liveUserError.postValue(UserError(lastNameError =  R.string.msg_error_lastName))
            isValid = false
        }
        if (isValid && liveUser.value?.mobileNumber.isNullOrEmpty()) {
            _liveUserError.postValue(UserError(mobileNumberError =  R.string.msg_error_mobileNumber))
            isValid = false
        }

        val PHONE_NUMBER_PATTERN = "^\\d{10}\$".toRegex()
        if (isValid && !PHONE_NUMBER_PATTERN.matches(liveUser.value?.mobileNumber!!)) {
            _liveUserError.postValue(UserError(mobileNumberError =  R.string.msg_error_valid_mobileNumber))
            isValid = false
        }
        if (isValid && liveUser.value?.email.isNullOrEmpty()) {
            _liveUserError.postValue(UserError(passwordError =  R.string.msg_error_password))
            isValid = false
        }

        if (isValid && liveUser.value?.userName.isNullOrEmpty()) {
            _liveUserError.postValue(UserError(userNameError =  R.string.msg_error_user_name))
            isValid = false
        }

        if (isValid && liveUser.value?.password.isNullOrEmpty()) {
            _liveUserError.postValue(UserError(passwordError =  R.string.msg_error_password))
            isValid = false
        }
        if (isValid && !BinderUtil.PASSWORD.matches(liveUser.value?.password!!)) {
            _liveUserError.postValue(UserError(passwordError =  R.string.msg_error_password_regex))
            isValid = false
        }

        if (isValid && confirmPassword.isEmpty()) {
            _liveUserError.postValue(UserError(confirmPasswordError =  R.string.msg_error_confirmPassword))
            isValid = false
        }
        if (isValid && confirmPassword != liveUser.value?.password) {
            _liveUserError.postValue(UserError(confirmPasswordError =  R.string.msg_error_passwordnotmatch))
            isValid = false
        }

        if(isValid) {
            _liveUserError.postValue(UserError())
            viewModelScope.launch(IO) {
                val lstUsers = repository.findUserByUserName(liveUser.value!!.userName,
                        liveUser.value!!.email)

                if (lstUsers?.isNotEmpty() == true) {
                    if (lstUsers.find { user -> user.email == liveUser.value!!.email } != null) {
                        _liveUserError.postValue(UserError(emailError = R.string.msg_error_email_name_exist))
                    } else {
                        _liveUserError.postValue(UserError(userNameError = R.string.msg_error_user_name_exist))
                    }
                }
                else if (lstUsers?.isEmpty() == true) {
                    liveRegistrationFlow.postValue(Resource.Status.LOADING)
                    val userInfo = liveUser.value!!.copy(password = liveUser.value!!.password.toSHA256Hash())
                    userInfo.uid = repository.doRegister(userInfo)
                    TIApplication.currUser = userInfo
                    liveRegistrationFlow.postValue(Resource.Status.SUCCESS)
                }
            }

        }

        return isValid
    }
}