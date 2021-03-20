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

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import com.piappstudio.travelinsurance.R
import kotlinx.android.synthetic.main.fragment_t_i_loader.*

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class TILoaderFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val loaderView = inflater.inflate(R.layout.fragment_t_i_loader, container, false)
        isCancelable = false
        loaderView.findViewById<ProgressBar>(R.id.commonProgressBar).visibility = View.VISIBLE
        return loaderView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, 0)
    }
    override fun onStart() {
        super.onStart()
        dialog?.let {
            val window = it.window
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            val windowParams = window?.attributes
            windowParams?.dimAmount = 0.1f
            windowParams?.flags != WindowManager.LayoutParams.FLAG_DIM_BEHIND
            window?.attributes = windowParams
        }
    }
}