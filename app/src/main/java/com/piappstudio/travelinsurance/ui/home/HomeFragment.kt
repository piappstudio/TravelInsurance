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

package com.piappstudio.travelinsurance.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavOptions
import androidx.navigation.compose.navArgument
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.common.TIApplication
import com.piappstudio.travelinsurance.databinding.FragmentHomeBinding
import com.piappstudio.travelinsurance.model.mbo.Vehicle
import com.piappstudio.travelinsurance.ui.vehicle.VehicleDetailFragment
import com.piappstudio.travelinsurance.ui.vehicle.VehicleDetailFragmentArgs
import com.piappstudio.travelinsurance.ui.vehicle.VehicleDetailFragmentDirections
import com.piappstudio.travelinsurance.ui.vehicle.VehicleViewModel
import dagger.hilt.android.AndroidEntryPoint


/** * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.user = TIApplication.currUser

        binding.btnBetterRate.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_vehicleListFragment)
        }
        binding.btnInsured.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_vehicleListFragment)
        }

        binding.btnNewVehicle.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToVehicleDetailFragment(true)
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}