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

package com.piappstudio.travelinsurance.model.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.piappstudio.travelinsurance.model.dao.VehicleDao
import com.piappstudio.travelinsurance.model.mbo.Vehicle

class VehiclePagingSource(private val repository: TravelRepository): PagingSource<Int, Vehicle>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override fun getRefreshKey(state: PagingState<Int, Vehicle>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Vehicle> {
        val position = params.key?: INITIAL_PAGE_INDEX
        val allVehicleList = repository.fetchVehicles()
        return LoadResult.Page(
                data = allVehicleList,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position-1,
                nextKey = if (allVehicleList.isEmpty()) null else position+1
        )

    }

}