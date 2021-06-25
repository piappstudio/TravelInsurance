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

package com.piappstudio.travelinsurance.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piappstudio.travelinsurance.model.dao.VehicleDao
import com.piappstudio.travelinsurance.model.mbo.User
import com.piappstudio.travelinsurance.model.mbo.Vehicle

@Database(entities = [User::class, Vehicle::class], version = 5, exportSchema = false)
abstract class TravelDatabase : RoomDatabase() {
    abstract fun  userDao():VehicleDao

    companion object {
     /*   private val MIGRATION_1_2: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create temp table and mke eventKey is optional
            }
        }
*/
    }
}