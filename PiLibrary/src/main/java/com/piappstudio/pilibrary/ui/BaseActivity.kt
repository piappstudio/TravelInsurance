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

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

open class TIBaseActivity :AppCompatActivity() {

    val TAG = TIBaseActivity::class.java.name
    private var mLoaderFragment:ProgressFragment? = null

    fun showProgressDialog(tag:String) {
        val curr = supportFragmentManager.findFragmentByTag(tag)
        curr?.let {
            val dialogFragment = it as? DialogFragment
            dialogFragment?.dismiss()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(it)
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
            if (it.isVisible) {
                Log.d(TAG, "Dismiss progress bar")
                it.dismissAllowingStateLoss()
            }
        }
    }
}