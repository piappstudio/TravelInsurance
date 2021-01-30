/*
 * Copyright (c) 2020 .All rights are reserved by PiAppStudio
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */
package com.piappstudio.travelinsurance.slider
/*
 * Copyright 2020 All rights are reserved by PiAppStudio
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.piappstudio.travelinsurance.R
import kotlinx.android.synthetic.main.fragment_intro_view.*

private const val INTRO_INFO = "INTRO_INFO"

/***
 * Fragment to render the introduction details
 */
class IntroViewFragment : Fragment() {
    private var introInfo: IntroInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            introInfo = it.getParcelable(INTRO_INFO)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        introInfo?.image?.let {
            view?.findViewById<ImageView>(R.id.ivIntro)?.setImageResource(it)
            ivIntro.contentDescription = introInfo?.title
        }
        this.view?.findViewById<TextView>(R.id.tvTitle)?.text = introInfo?.title
        this.view?.findViewById<TextView>(R.id.tvDateInterval)?.text = introInfo?.description
    }

    companion object {
        @JvmStatic
        fun newInstance(introInfo: IntroInfo) =
            IntroViewFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(INTRO_INFO, introInfo)
                }
            }
    }
}