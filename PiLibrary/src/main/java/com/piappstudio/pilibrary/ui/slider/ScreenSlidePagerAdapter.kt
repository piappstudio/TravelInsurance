/*
 * Copyright 2021 All rights are reserved by Pi App Studio
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.piappstudio.pilibrary.ui.slider

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.piappstudio.pilibrary.ui.slider.IntroInfo

/**
 * To render the {IntroViewFragment} in view pages
 */
class ScreenSlidePagerAdapter(fragment: Fragment, private val lstOfPageContents: List<IntroInfo>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return lstOfPageContents.size
    }

    override fun createFragment(position: Int): Fragment {
        return IntroViewFragment.newInstance(lstOfPageContents[position])
    }

}