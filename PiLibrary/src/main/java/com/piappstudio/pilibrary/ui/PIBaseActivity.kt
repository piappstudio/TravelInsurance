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

package com.piappstudio.pilibrary.ui

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

open class PIBaseActivity :AppCompatActivity() {

    val TAG = PIBaseActivity::class.java.name
    private var mLoaderFragment: ProgressFragment? = null

    fun showProgressDialog(tag:String) {
        Log.d(TAG, "Show progress bar")
        val curr = supportFragmentManager.findFragmentByTag(tag)
        if(curr!=null) {
            val dialogFragment = curr as? DialogFragment
            dialogFragment?.dismiss()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(curr)
            transaction.commit()
        }
        mLoaderFragment = ProgressFragment()
        mLoaderFragment?.isCancelable = false

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(mLoaderFragment!!, tag)
        transaction.commitAllowingStateLoss()
    }

    fun dismissProgressDialog(tag: String) {
        mLoaderFragment?.let {
            Log.d(TAG, "Dismiss progress bar")
            it.dismissAllowingStateLoss()
        }
    }
}