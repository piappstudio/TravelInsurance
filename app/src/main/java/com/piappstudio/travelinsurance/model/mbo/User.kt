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

package com.piappstudio.travelinsurance.model.mbo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User (@PrimaryKey var uid:Long?=null, var firstName:String="", var lastName:String="",
                 var mobileNumber:String="", var userName: String="", var password: String ="",
                 var email:String=""): Parcelable {

                     fun fullName() = "${firstName.capitalize()} ${lastName.capitalize()}"
}