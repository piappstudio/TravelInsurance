/*
 * Copyright 2020 All rights are reserved by Pi App Studio
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.piappstudio.travelinsurance.slider

import android.os.Parcel
import android.os.Parcelable

/**
 * To capture the introduction screen
 */
class IntroInfo : Parcelable {
    var image: Int?
    var title: String?
    var description: String?

    constructor(image: Int?, title: String?, description: String?) {
        this.image = image
        this.title = title
        this.description = description
    }

    init {
        image = 0
        title = null
        description = null
    }

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(image)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IntroInfo> {
        override fun createFromParcel(parcel: Parcel): IntroInfo {
            return IntroInfo(parcel)
        }

        override fun newArray(size: Int): Array<IntroInfo?> {
            return arrayOfNulls(size)
        }
    }


}