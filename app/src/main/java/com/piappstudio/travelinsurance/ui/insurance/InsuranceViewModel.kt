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
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.piappstudio.pilibrary.model.InsuranceInfoItem
import com.piappstudio.pilibrary.network.PiDataRepository
import com.piappstudio.pilibrary.utility.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScoped
class InsuranceViewModel @Inject constructor(apiRepository:PiDataRepository) : ViewModel() {

    private val _lstInsurance = MutableStateFlow<Resource<List<InsuranceInfoItem>?>>(Resource.idle(null))
    val lstInsuranace = _lstInsurance

    private val _selectedInsuranceItem = MutableLiveData<InsuranceInfoItem>()
    val selectedInsuranceInfo:LiveData<InsuranceInfoItem> = _selectedInsuranceItem

    init {
        viewModelScope.launch {
            apiRepository.fetchInsurances().collect {
                _lstInsurance.value = it
            }
        }
    }

    fun updateSelectedInsuranceInfo(insuranceInfo:InsuranceInfoItem) {
        _selectedInsuranceItem.postValue(insuranceInfo)
    }
}