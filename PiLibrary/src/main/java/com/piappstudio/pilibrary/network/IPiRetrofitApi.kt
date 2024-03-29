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

package com.piappstudio.pilibrary.network

import com.piappstudio.pilibrary.model.InsuranceInfoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

// Retorfit interface, the retorfit interface will takes care of implement this one
interface IPiRetrofitApi {

    @GET("main/service/insurance.json")
    suspend fun fetchInsurances():Response<List<InsuranceInfoItem>>

    @GET("service/vehicle.json")
    suspend fun fetchVehicles()
}