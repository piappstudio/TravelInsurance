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

package com.piappstudio.pilibrary.model

import android.content.Context
import com.piappstudio.pilibrary.common.readJsonFile

class Auto(val autoInfo: List<AutoInfo>?) {
    companion object {

        var autoInfo:List<AutoInfo>? = null

        fun readJsonFile(context: Context, fileName:String):String{
            return context.readJsonFile(fileName, true)
        }
    }
}


data class AutoInfo(val name:String, val makes:List<MakeInfo>)
data class MakeInfo(val code:String?, val value:String?, val title:String?, val models:List<MakeInfo>)
