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

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.piappstudio.travelinsurance.model.dao.UserDao
import com.piappstudio.travelinsurance.model.mbo.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class TravelDatabase : RoomDatabase() {
    abstract fun  userDao():UserDao

    companion object {
        @Volatile
        private var INSTANCE:TravelDatabase? = null
        fun getDatabase(context: Context):TravelDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    TravelDatabase::class.java,
                    "travel_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}