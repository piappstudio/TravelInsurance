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
package com.piappstudio.pilibrary.ui.slider
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
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.piappstudio.pilibrary.R
import com.piappstudio.pilibrary.databinding.FragmentIntroViewBinding

private const val INTRO_INFO = "INTRO_INFO"

/***
 * Fragment to render the introduction details
 */
class IntroViewFragment : Fragment() {
    private var introInfo: IntroInfo? = null
    private var _binding:FragmentIntroViewBinding?= null
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
        _binding = FragmentIntroViewBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return _binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        introInfo?.image?.let {
            _binding?.ivIntro?.setImageResource(it)
            _binding?.ivIntro?.contentDescription = introInfo?.title
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