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

package com.piappstudio.travelinsurance.util

data class Resource<out T>(val status: Status, val data: T?, val message: String?){

    companion object {
        //Handles success
        fun <T> success(data: T): Resource<T> = Resource(
            status = Status.SUCCESS, data = data, message = null)
        //Handles Loading
        fun <T> loading(data: T?): Resource<T> = Resource(
            status = Status.LOADING, data = data, message = null)
        //Handles Error
        fun <T> error(data: T?, message: String): Resource<T> = Resource(
            status = Status.ERROR, data = data, message = message)
        fun<T> idle(data:T?) = Resource (status = Status.NONE, data = data, message = null)

    }
    enum class Status {
        NONE,
        SUCCESS,
        ERROR,
        LOADING
    }
}
