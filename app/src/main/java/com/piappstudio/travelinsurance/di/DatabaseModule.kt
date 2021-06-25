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

package com.piappstudio.travelinsurance.di

import android.content.Context
import androidx.room.Room
import com.piappstudio.travelinsurance.common.TravelDatabase
import com.piappstudio.travelinsurance.model.dao.VehicleDao
import com.piappstudio.travelinsurance.model.repository.TravelRepository
import com.piappstudio.travelinsurance.ui.vehicle.VehicleViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTravelDatabase(@ApplicationContext context: Context):TravelDatabase {
        return Room.databaseBuilder(context,
            TravelDatabase::class.java,
            "travel_database").fallbackToDestructiveMigration().build()
    }


    @Provides
    fun providesVehicleDao(database: TravelDatabase): VehicleDao {
        return database.userDao()
    }
}

