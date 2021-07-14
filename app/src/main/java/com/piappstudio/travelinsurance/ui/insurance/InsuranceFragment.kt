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

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.common.readJsonFile
import com.piappstudio.travelinsurance.model.mbo.InsuranceInfo
import com.piappstudio.travelinsurance.model.mbo.InsuranceInfoItem
import com.piappstudio.travelinsurance.ui.vehicle.VehicleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class InsuranceFragment : Fragment() {

    private val insuranceViewModel: InsuranceViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    val insuranceInfo:InsuranceInfo by insuranceViewModel.lstInsuranceProvider.observeAsState(
                        initial = InsuranceInfo()
                    )
                    insuranceInfo.insuranceInfo?.let {
                        renderInsuranceList(lstInsurance = it)

                    }
                }
            }
        }
    }

    fun initUI() {
        lifecycleScope.launch (Dispatchers.IO) {
            val jsonString = requireActivity().readJsonFile("insurance.json")
            insuranceViewModel.parseJson(jsonString)
        }
    }

    @Composable
    fun renderInsuranceList(lstInsurance:List<InsuranceInfoItem>) {
       LazyColumn() {
           items(lstInsurance) { message ->
               itemInsuranceRow(insuranceItem = message)
           }
       }
    }

    @Composable
    fun itemInsuranceRow(insuranceItem:InsuranceInfoItem) {
        val padding = 16.dp
        val height = 10.dp
        Box (Modifier.padding(padding)) {
            Text(text = insuranceItem.supplierName ?: stringResource(R.string.not_available),
            style = typography.caption)
            Spacer(modifier = Modifier.height(height))
            Row(Modifier.padding(padding)) {
                Text(text = insuranceItem.irdaPackagePremium.toString(), fontStyle = FontStyle.Italic, style = typography.overline)
                Text(text = insuranceItem.finalPremium.toString(), fontStyle = FontStyle.Italic, style = typography.body1)
            }
        }
    }
}