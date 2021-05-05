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

package com.piappstudio.travelinsurance.ui.vehicle

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.common.OnItemClickListener
import com.piappstudio.travelinsurance.databinding.FragmentVehicleListBinding
import com.piappstudio.travelinsurance.model.mbo.Vehicle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * create an instance of this fragment.
 */
class VehicleListFragment : Fragment() {

    private var _binding:FragmentVehicleListBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        VehiclePageAdapter(listener = object : OnItemClickListener<Vehicle> {
            override fun onClick(item: Vehicle?) {
                viewModel.currVehicleInfo = item
                findNavController().navigate(R.id.action_vehicleListFragment_to_vehicleDetailFragment)
            }
        })
    }

    private val viewModel by lazy {
        VehicleViewModel.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //insertDummyData()
        _binding = FragmentVehicleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_vehicle_list, menu)
        val searchView: SearchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                search(query)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvVehicleList.adapter = adapter
        binding.rvVehicleList.layoutManager = LinearLayoutManager(context)
        search(null)
    }

    fun search(query:String?) {
        lifecycleScope.launch {
            viewModel.getSearchResultStream(query?:"").collectLatest {
                adapter.submitData(it)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}