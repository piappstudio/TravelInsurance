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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.piappstudio.travelinsurance.R
import com.piappstudio.travelinsurance.common.OnItemClickListener
import com.piappstudio.travelinsurance.databinding.ItemVehicleRowBinding
import com.piappstudio.travelinsurance.model.mbo.Vehicle
import com.piappstudio.travelinsurance.ui.vehicle.VehiclePageAdapter.*

class VehiclePageAdapter(val listener:OnItemClickListener<Vehicle>) : PagingDataAdapter<Vehicle, VehicleViewHolder> (diffCallback) {

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Vehicle>() {
            override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean =
                    oldItem.uid == newItem.uid

            override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean =
                    oldItem == newItem
        }
    }

    open inner class VehicleViewHolder(private val binding: ItemVehicleRowBinding):RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onClick(getItem(bindingAdapterPosition))
            }
        }

        fun onBind(position: Int) {
            val vehicle = getItem(position)
            binding.vrTitle.text =  vehicle?.vMake+" "+vehicle?.vModel
            binding.vrDescription.text = vehicle?.vYear
            when (vehicle?.vType) {
                "Car"-> {
                    binding.vrImage.setImageResource(R.drawable.ic_car)
                }
                "Two Wheeler"-> {
                    binding.vrImage.setImageResource(R.drawable.ic_motorcycle)
                }
                "Commercial"-> {
                    binding.vrImage.setImageResource(R.drawable.ic_ads)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val binding = ItemVehicleRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleViewHolder(binding)
    }
}


