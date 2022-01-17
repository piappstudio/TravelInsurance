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

import android.content.Context
import com.google.gson.Gson
import com.piappstudio.pilibrary.common.isFileExist
import com.piappstudio.pilibrary.common.isNetworkAvailable
import com.piappstudio.pilibrary.common.saveFileOnCache
import com.piappstudio.pilibrary.di.NetworkModule
import com.piappstudio.pilibrary.utility.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PiDataRepository @Inject constructor (@ApplicationContext val context: Context,
                                            @NetworkModule.LocalAccess
                                         private val localRepo:IPiDataSource,
                                            @NetworkModule.RemoteAccess
                                         private val networkRepo:IPiDataSource) {

    // To fetch insurance details in the form of flow
    suspend fun fetchInsurances() = flow {
        emit(Resource.loading())
        if (context.isNetworkAvailable() && !context.isFileExist(FILE_INSURANCE)) {
            val response = networkRepo.fetchInsurances()
            if (response.data != null) {
                context.saveFileOnCache(Gson().toJson(response.data), FILE_INSURANCE)
                emit(Resource.success(response.data))
            }else {
                emit(response)
            }
        } else {
            val response = localRepo.fetchInsurances()
            emit(Resource.success(response.data))
        }
    }
}