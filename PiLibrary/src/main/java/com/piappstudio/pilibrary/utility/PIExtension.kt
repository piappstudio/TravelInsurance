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

package com.piappstudio.pilibrary.utility

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Activity.dismissKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (inputMethodManager.isAcceptingText)
        inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, /*flags:*/ 0)
}
fun Double.roundTo(n : Int) : Double {
    return "%.${n}f".format(this).toDouble()
}

fun Double.addComma():String {
    val dec = DecimalFormat("#,###.00")
    return dec.format(this)
}
fun Date.toDisplayDateFormat(): String {
    val simpleDateFormat = SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
}
fun Context.showToast(message:String) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.BOTTOM, Gravity.CENTER, 0)
    toast.show()
}