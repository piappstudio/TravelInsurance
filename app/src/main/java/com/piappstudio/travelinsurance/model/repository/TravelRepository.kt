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
import com.piappstudio.travelinsurance.model.dao.VehicleDao
import com.piappstudio.travelinsurance.model.mbo.User
import com.piappstudio.travelinsurance.model.mbo.Vehicle

class TravelRepository(private val vehicleDao: VehicleDao) {

    fun fetchVehicleList(query:String, userId:Long): PagingSource<Int, Vehicle> {
        return vehicleDao.getAllVehicleList(query, userId)
    }

    suspend fun fetchVehicles():List<Vehicle> {
        return vehicleDao.getAllVehicles()
    }

    suspend fun findUserByUserName(userName: String, email:String):  List<User>? {
        return vehicleDao.findUserByUserName(userName, email)
    }

    suspend fun findByUserNamePassword(userName:String, password:String):User? {
        return vehicleDao.findByUserNamePassword(userName, password)
    }

    suspend fun doRegister(user: User):Long {
        return vehicleDao.insert(user)
    }

    suspend fun addVehicle(vehicle: Vehicle):Long {
        return vehicleDao.insert(vehicle)
    }
    suspend fun updateVehicle(vehicle: Vehicle) {
        vehicleDao.updateVehicle(vehicle)
    }

}