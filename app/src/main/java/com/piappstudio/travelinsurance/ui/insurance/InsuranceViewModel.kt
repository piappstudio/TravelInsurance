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
import com.google.gson.reflect.TypeToken
import com.piappstudio.travelinsurance.model.mbo.InsuranceInfoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class InsuranceViewModel @Inject constructor() : ViewModel() {
    private val _lstInsuranceProviders = MutableLiveData<List<InsuranceInfoItem>>()
    val lstInsuranceProvider: LiveData<List<InsuranceInfoItem>> = _lstInsuranceProviders

    private val _selectedInsuranceItem = MutableLiveData<InsuranceInfoItem>()
    val selectedInsuranceInfo:LiveData<InsuranceInfoItem> = _selectedInsuranceItem
    fun parseJson(jsonString:String) {
        val myType = object : TypeToken<List<InsuranceInfoItem>>() {}.type
        val insuranceInfo =  Gson().fromJson<List<InsuranceInfoItem>>(jsonString, myType).distinctBy { it.supplierName }.sortedByDescending {
            it.finalPremium
        }
        _lstInsuranceProviders.postValue(insuranceInfo)
    }

    fun updateSelectedInsuranceInfo(insuranceInfo:InsuranceInfoItem) {
        _selectedInsuranceItem.postValue(insuranceInfo)
    }
}