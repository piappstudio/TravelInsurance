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

package com.piappstudio.travelinsurance.ui.vehicle

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.piappstudio.travelinsurance.common.TIApplication
import com.piappstudio.travelinsurance.model.mbo.Vehicle
import com.piappstudio.travelinsurance.model.repository.TravelRepository
import kotlinx.coroutines.flow.Flow

class VehicleViewModel(val repository: TravelRepository):ViewModel() {

    var currVehicleInfo:Vehicle? = null

    fun getSearchResultStream(query:String):Flow<PagingData<Vehicle>> {
        return Pager(
                config = PagingConfig(10, 10, true,
                        10*3),
                pagingSourceFactory = {repository.fetchVehicleList(query)}
        ).flow
    }

    companion object {
        @Volatile
        private var INSTANCE:VehicleViewModel?= null
        fun getInstance():VehicleViewModel {
            return INSTANCE?: synchronized(this) {
                val viewModel = VehicleViewModel(TIApplication.INSTANCE!!.repository)
                INSTANCE = viewModel
                viewModel
            }
        }

    }
}