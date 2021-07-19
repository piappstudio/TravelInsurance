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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.piappstudio.pilibrary.utility.addComma
import com.piappstudio.pilibrary.utility.roundTo
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.common.theme.TravelInsuranceTheme
import com.piappstudio.travelinsurance.model.mbo.InsuranceInfoItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class InsuranceDetailFragment : Fragment() {

    @Inject
    lateinit var insuranceViewModel: InsuranceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TravelInsuranceTheme {
                    val insuranceInfoItem: InsuranceInfoItem by insuranceViewModel.selectedInsuranceInfo.observeAsState(
                        initial = InsuranceInfoItem()
                    )
                    renderInsuranceItemDetail(infoItem = insuranceInfoItem)
                }
            }
        }
    }

    @Composable
    fun renderInsuranceItemDetail(infoItem: InsuranceInfoItem) {

        val padding = 8.dp
        Surface {
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .fillMaxWidth().wrapContentHeight(align = Alignment.Top)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(text = infoItem.supplierName.toString(), style = typography.h4)
                    Spacer(modifier = Modifier.height(16.dp))
                    renderRow(
                        first = stringResource(R.string.title_premium_amount),
                        last = infoItem.breakup?.finalPremium?.roundTo(2)?.addComma() ?: ""
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    renderRow(
                        first = stringResource(R.string.title_gst_18),
                        last = infoItem.breakup?.finalPremium?.times(0.18)?.roundTo(2)?.addComma()
                            ?: ""
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    renderRow(
                        first = stringResource(R.string.title_you_will_pay),
                        last = infoItem.breakup?.finalPremium?.times(0.18)
                            ?.plus(infoItem.breakup.finalPremium)?.roundTo(2)?.addComma() ?: ""
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ExtendedFloatingActionButton(
                        text = { Text(text = stringResource(R.string.btn_pay_securely)) },
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )

                }
            }
        }
    }

    @Composable
    fun renderRow(first: String, last: String) {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = first,
                Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.TopStart),
                textAlign = TextAlign.Left,
                style = typography.body1
            )
            Text(
                text = last,
                Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.TopEnd),
                textAlign = TextAlign.End,
                style = typography.subtitle1
            )
        }
    }

    @Preview
    @Composable
    fun renderRowPreview() {
        renderRow(first = "Muruga", last = "Siva")
    }
}