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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.WorkerThread
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.piappstudio.pilibrary.ui.PIBaseActivity
import com.piappstudio.pilibrary.utility.Resource
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.databinding.FragmentVehicleDetailBinding
import com.piappstudio.travelinsurance.model.mbo.Vehicle
import com.piappstudio.travelinsurance.model.mbo.json.Auto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class VehicleDetailFragment : Fragment() {

    private var _binding:FragmentVehicleDetailBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var viewModel:VehicleViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVehicleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    fun setupUI() {
        binding.vehicleModel = viewModel
        binding.lifecycleOwner = this

        val isNewVehicle = navArgs<VehicleDetailFragmentArgs>().value.isNew
        viewModel.mutIsUpdate.postValue(isNewVehicle)
        if (isNewVehicle) {
            viewModel.currVehicleInfo = MutableLiveData(Vehicle())
        }
        // Set the adapter when values gets changes
        viewModel.liveVehicleType.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.actVType.setAdapter(ArrayAdapter<String>(requireContext(), R.layout.list_popup_window_item, it))
        })

        viewModel.liveVehicleMake.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            binding.actVMake.setAdapter(ArrayAdapter<String>(requireContext(), R.layout.list_popup_window_item, it))
        })

        viewModel.liveVehicleModel.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
           binding.actModel.setAdapter(ArrayAdapter<String>(requireContext(), R.layout.list_popup_window_item, it))
        })

        binding.actVType.doOnTextChanged { text, _, _, _ ->
            viewModel.onSelectType(text.toString())
        }
        binding.actVMake.doOnTextChanged { text, _, _, _ ->
            viewModel.onSelectMake(text.toString())

        }
        val years = mutableListOf<String>()
        for(i in 1800.. Calendar.getInstance(Locale.getDefault()).get(Calendar.YEAR)) {
            years.add(i.toString())
        }
        binding.actYear.setAdapter(ArrayAdapter(requireContext(), R.layout.list_popup_window_item, years))

        binding.btnAdd.setOnClickListener {

            val activity = activity as? PIBaseActivity
            lifecycleScope.launch {
                viewModel.onSubmit().collect {
                    when (it) {
                        Resource.Status.LOADING -> {
                            activity?.showProgressDialog("Loading")
                        }
                        Resource.Status.SUCCESS -> {
                            activity?.dismissProgressDialog("Loading")
                            findNavController().navigateUp()
                        }
                        else -> {
                            activity?.dismissProgressDialog("Loading")
                        }
                    }
                }
            }

        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (Auto.autoInfo == null) {
                val jsonString = Auto.readJsonFile(requireContext(), "vehicle.json")
                Auto.autoInfo = viewModel.parseAutoJson(jsonString)
            }
            Auto.autoInfo?.let {
                viewModel.updateType(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}