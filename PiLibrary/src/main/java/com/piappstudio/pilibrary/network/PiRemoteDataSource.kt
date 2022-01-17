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
import com.piappstudio.pilibrary.utility.PIError
import com.piappstudio.pilibrary.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


// Class that helps you to fetch the data from server (network)
class PiRemoteDataSource @Inject constructor(val IPiRetrofitApi: IPiRetrofitApi):IPiDataSource {
    override suspend fun fetchInsurances():Resource<List<InsuranceInfoItem>?> {
        return withContext(Dispatchers.IO) {
             val response = IPiRetrofitApi.fetchInsurances()
            if (response.isSuccessful) {
               Resource.success(response.body())
            } else {
                Resource.error(null, error = PIError(response.code()))
            }
        }
    }
}