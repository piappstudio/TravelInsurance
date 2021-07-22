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

package com.piappstudio.travelinsurance.common

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun Context.readJsonFile(fileName:String):String{

    val returnString = StringBuilder()
    var inStream: InputStreamReader? = null
    var inputBuffer: BufferedReader? = null
    try {
        val stream = assets.open(fileName, Context.MODE_PRIVATE)
        inStream = InputStreamReader(stream)
        inputBuffer = BufferedReader(inStream)
        do {
            val line = inputBuffer.readLine()
            if (line!=null) {
                returnString.append(line)
            }
        } while (line!=null)
    } catch (exception:Exception) {

    }
    finally {
        inStream?.close()
        inputBuffer?.close()
    }
    return returnString.toString()
}
