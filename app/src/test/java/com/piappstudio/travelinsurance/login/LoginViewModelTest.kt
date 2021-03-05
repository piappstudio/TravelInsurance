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

package com.piappstudio.travelinsurance.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.mbo.User
import com.piappstudio.travelinsurance.mock
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify


class LoginViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var viewModel: LoginViewModel
    private val errorUserName:Observer<Int> = mock()
    private val errorPassword:Observer<Int> = mock()

    @Before
    fun before() {
        viewModel = LoginViewModel()
        viewModel.errorUser.observeForever(errorUserName)
        viewModel.errorPass.observeForever(errorPassword)
    }

    @Test
    fun test_onClickLogin() {
        viewModel.onClickLogin()
        assertEquals(viewModel.errorPass.value, R.string.msg_error_password)
        assertEquals(viewModel.errorUser.value, R.string.msg_error_user_name)
    }
}