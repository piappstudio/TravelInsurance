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

import com.piappstudio.pilibrary.model.InsuranceInfoItem

const val FILE_INSURANCE = "insurance.json"
// Custom interface, that holds all the operations
interface IPiDataSource {
    suspend fun fetchInsurances(): List<InsuranceInfoItem>?
}