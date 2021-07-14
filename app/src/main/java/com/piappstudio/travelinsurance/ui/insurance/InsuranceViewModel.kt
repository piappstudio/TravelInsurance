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

package com.piappstudio.travelinsurance.ui.insurance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.piappstudio.travelinsurance.model.mbo.InsuranceInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InsuranceViewModel @Inject constructor() : ViewModel() {
    val _lstInsuranceProviders = MutableLiveData<InsuranceInfo>()
    val lstInsuranceProvider: LiveData<InsuranceInfo> = _lstInsuranceProviders

    fun parseJson(jsonString:String) {
       val insuranceInfo =  Gson().fromJson(jsonString, InsuranceInfo::class.java)
        _lstInsuranceProviders.postValue(insuranceInfo)
    }
}