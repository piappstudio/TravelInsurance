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

package com.piappstudio.pilibrary.common

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import java.io.*
import android.net.NetworkInfo

import androidx.core.content.ContextCompat.getSystemService

import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService





fun Context.isFileExist(fileName: String):Boolean {
    return File(this.cacheDir, fileName).exists()
}
fun Context.readJsonFile(fileName:String, isAssetFolder:Boolean = false):String{

    val returnString = StringBuilder()
    var inStream: InputStreamReader? = null
    var inputBuffer: BufferedReader? = null
    try {

        inStream = if (isAssetFolder) {
            InputStreamReader(this.assets.open(fileName))
        } else {
            val stream =  File(this.cacheDir, fileName).inputStream()
            InputStreamReader(stream)
        }

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

fun Context.saveFileOnCache(jsonResponse:String, fileName:String) {
    val file = File(this.cacheDir, fileName)
    val fileWrite = FileWriter(file.absoluteFile)
    val bufferWriter = BufferedWriter(fileWrite)
    bufferWriter.write(jsonResponse)
    bufferWriter.close()
}
fun Context.isNetworkAvailable():Boolean {
    val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}






