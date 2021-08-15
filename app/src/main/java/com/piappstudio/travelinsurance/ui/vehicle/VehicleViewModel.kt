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

package com.piappstudio.travelinsurance.ui.vehicle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.piappstudio.pilibrary.model.AutoInfo
import com.piappstudio.pilibrary.model.MakeInfo
import com.piappstudio.pilibrary.utility.Resource
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.common.TIApplication
import com.piappstudio.travelinsurance.model.mbo.Vehicle
import com.piappstudio.travelinsurance.model.mbo.VehicleError
import com.piappstudio.travelinsurance.model.repository.TravelRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityScoped
class VehicleViewModel @Inject constructor (val repository: TravelRepository): ViewModel() {

    var currVehicleInfo:MutableLiveData<Vehicle> = MutableLiveData()
    var autoInfo:List<AutoInfo>? = null

    var mutIsUpdate:MutableLiveData<Boolean> = MutableLiveData(false)

    // For type
    private val _liveVehicleType:MutableLiveData<List<String>> = MutableLiveData()
    val liveVehicleType: LiveData<List<String>> = _liveVehicleType

    // For make
    private val _liveVehicleMake:MutableLiveData<List<String>> = MutableLiveData()
    val liveVehicleMake:LiveData<List<String>> = _liveVehicleMake

    // For model
    private val _liveVehicleModel:MutableLiveData<List<String>> = MutableLiveData()
    val liveVehicleModel:LiveData<List<String>> = _liveVehicleModel

    private  val _mutErrorVehicleInfo = MutableLiveData(VehicleError())
    val errorVehicleInfo: LiveData<VehicleError> = _mutErrorVehicleInfo

    fun getSearchResultStream(query:String, userId:Long):Flow<PagingData<Vehicle>> {
        return Pager(
                config = PagingConfig(10, 10, true,
                        10*3),
                pagingSourceFactory = {repository.fetchVehicleList(query, userId)}
        ).flow
    }


    fun parseAutoJson(jsonString:String):List<AutoInfo> {

        val jsonObject = JsonParser.parseString(jsonString).asJsonObject
        val gson = Gson()
        val arrAuto = mutableListOf<AutoInfo>()
        for (key in jsonObject.keySet()) {
            val lstMake = gson.fromJson(jsonObject.get(key), Array<MakeInfo>::class.java)
            val autoInfo = AutoInfo(key, lstMake.asList())
            arrAuto.add(autoInfo)
        }

        return arrAuto
    }

    fun updateType(lstAuto:List<AutoInfo>) {
        autoInfo = lstAuto
        _liveVehicleType.postValue(lstAuto.map { it.name })
    }

    fun onSelectType(text:String) {
        if (text.isEmpty()) return
        autoInfo?.first { it.name == text }?.makes?.let {
            val lstMake = it.map { make -> make.title!! }
             _liveVehicleMake.postValue(lstMake)
            currVehicleInfo.postValue(currVehicleInfo.value?.also { vehicle ->
                val make = lstMake.filter { make-> make == vehicle.vMake }
                if (make.isEmpty()) {
                    vehicle.vMake = ""
                    vehicle.vModel = ""
                }
            })
         }

        _liveVehicleModel.postValue(emptyList())
    }

    fun onSelectMake(text:String) {
        if (text.isEmpty()) return
        autoInfo?.first{it.name == currVehicleInfo.value?.vType}?.makes?.first{it.title == text}?.models?.let {
            val lstModel  = it.map { makeInfo -> makeInfo.title!! }
            _liveVehicleModel.postValue(lstModel)
            currVehicleInfo.postValue(currVehicleInfo.value?.also { vehicle ->
                val make = lstModel.filter { make-> make == vehicle.vModel }
                if (make.isEmpty()) {
                    vehicle.vModel = ""
                }

            })
        }
    }

    private fun isValidData():Boolean {
        val vehicle = currVehicleInfo.value ?: return false
        if (vehicle.vType.isEmpty()) {
            _mutErrorVehicleInfo.postValue(VehicleError(vType = R.string.msg_error_selectType))
            return false
        }
        if (vehicle.vMake.isEmpty()) {
            _mutErrorVehicleInfo.postValue(VehicleError(vMakeError = R.string.msg_error_selectMake))
            return false
        }
        if (vehicle.vModel.isEmpty()) {
            _mutErrorVehicleInfo.postValue(VehicleError(vModelError = R.string.msg_error_selectModel))
            return false
        }
        if (vehicle.vYear.isEmpty()) {
            _mutErrorVehicleInfo.postValue(VehicleError(vYearError = R.string.msg_error_selectyear))
            return false
        }

        if (vehicle.vNumber.isEmpty()) {
            _mutErrorVehicleInfo.postValue(VehicleError(vNumberError = R.string.msg_error_vehicle_number))
            return false
        }
        if(vehicle.vEngineNumber.isEmpty()) {
            _mutErrorVehicleInfo.postValue(VehicleError(vEngineNumberError = R.string.msg_error_engine_number))
            return false
        }
        if (vehicle.zip.isEmpty()) {
            _mutErrorVehicleInfo.postValue(VehicleError(zipError = R.string.msg_error_zip_code))
            return false
        }
        if (vehicle.ownerStatus.isEmpty()) {
            _mutErrorVehicleInfo.postValue(VehicleError(ownerStatusError = R.string.msg_error_owner_status))
            return false
        }
        if (vehicle.vUsage.isEmpty()) {
            _mutErrorVehicleInfo.postValue(VehicleError(vUsageError = R.string.enter_usage_kilometers))
            return false
        }
        if (vehicle.annualMillage.isEmpty()) {
            _mutErrorVehicleInfo.postValue(VehicleError(annualMillageError = R.string.msg_error_yearly_millage))
            return false
        }

        return true
    }
    fun onSubmit() = flow {
        emit(Resource.Status.LOADING)
        if (isValidData()) {
                if(currVehicleInfo.value?.uid != null) {
                    repository.updateVehicle(currVehicleInfo.value!!)
                    println("Completed")
                } else {
                    currVehicleInfo.value?.userId = TIApplication.currUser?.uid?:0
                    repository.addVehicle(currVehicleInfo.value!!)
                    println("Added")
                }
            emit(Resource.Status.SUCCESS)
        } else {
            emit(Resource.Status.ERROR)
        }

    }
}