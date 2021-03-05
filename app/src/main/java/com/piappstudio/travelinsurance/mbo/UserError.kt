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

package com.piappstudio.travelinsurance.mbo

import com.piappstudio.travelinsurance.R

data class UserError (var firstNameError:Int= R.string.empty,
                      var lastNameError:Int= R.string.empty,
                      var mobileNumberError:Int = R.string.empty,
                      var emailError:Int=R.string.empty,
                      var userNameError:Int = R.string.empty,
                      var passwordError:Int=R.string.empty,
                      var confirmPasswordError:Int=R.string.empty) {
}