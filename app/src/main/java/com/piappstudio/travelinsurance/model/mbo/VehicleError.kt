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

import com.piappstudio.travelinsurance.R

data class VehicleError (var vType:Int? = R.string.empty,
                         var vNumberError:Int? = R.string.empty,
                         var vYearError:Int? = R.string.empty,
                         var vMakeError:Int? = R.string.empty,
                         var vModelError:Int? = R.string.empty,
                         var vEngineNumberError:Int? = R.string.empty,
                         var zipError:Int?= R.string.empty,
                         var ownerStatusError:Int?= R.string.empty,
                         var vUsageError:Int? = R.string.empty,
                         var annualMillageError:Int? = R.string.empty)