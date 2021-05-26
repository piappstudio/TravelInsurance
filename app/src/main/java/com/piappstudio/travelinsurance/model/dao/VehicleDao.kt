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

package com.piappstudio.travelinsurance.model.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.piappstudio.travelinsurance.model.mbo.User
import com.piappstudio.travelinsurance.model.mbo.Vehicle

@Dao
interface VehicleDao {

    @Query("SELECT * FROM user WHERE (userName=:userName OR email=:userName) AND password=:password")
    suspend fun findByUserNamePassword(userName:String, password:String):User?

    @Query("SELECT * FROM user WHERE userName=:userName OR email=:email")
    suspend fun findUserByUserName(userName: String, email:String):List<User>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User):Long

    @Query("DELETE FROM user")
    suspend fun deleteAll()


    @Query("SELECT * FROM VEHICLE WHERE userId = :userId AND (vMake  like '%'||:query||'%' OR vYear like '%'||:query||'%')")
    fun getAllVehicleList(query:String, userId:Long): PagingSource<Int, Vehicle>

    @Query("SELECT * FROM VEHICLE")
    suspend fun getAllVehicles(): List<Vehicle>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vehicle: Vehicle):Long

    @Query("DELETE FROM Vehicle")
    suspend fun deleteAllVehicle()

    @Update(entity = Vehicle::class)
    suspend fun updateVehicle(vararg event: Vehicle)
}