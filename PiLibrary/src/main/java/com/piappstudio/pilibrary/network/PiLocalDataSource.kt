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
import com.google.gson.reflect.TypeToken
import com.piappstudio.pilibrary.common.readJsonFile
import com.piappstudio.pilibrary.model.InsuranceInfoItem
import com.piappstudio.pilibrary.utility.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**To read response from cache folder*/
class PiLocalDataSource @Inject constructor(@ApplicationContext val context:Context): IPiDataSource {
    override suspend fun fetchInsurances(): Resource<List<InsuranceInfoItem>?> {
        return withContext(Dispatchers.IO) {
            val json = context.readJsonFile(FILE_INSURANCE)
            val myType = object : TypeToken<List<InsuranceInfoItem>>() {}.type
            val result = Gson().fromJson<List<InsuranceInfoItem>>(json, myType)
            Resource.success(result)
        }
    }
}