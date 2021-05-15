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

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vehicle(@PrimaryKey val uid:Int? = null, var vNumber:String="", var vYear:String="",
                   var vMake:String="",var vModel:String="",
                   var vEngineNumber:String="", var zip:String="", var ownerStatus:String="",
                   var vUsage:String="", var usageKilometers:String="", var annualMillage:String="", var vType:String="")