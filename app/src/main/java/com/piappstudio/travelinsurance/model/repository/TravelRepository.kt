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

package com.piappstudio.travelinsurance.model.repository

import com.piappstudio.travelinsurance.model.dao.UserDao
import com.piappstudio.travelinsurance.model.mbo.User

class TravelRepository(private val userDao: UserDao) {

    suspend fun findUserByUserName(userName: String, email:String):  List<User>? {
        return userDao.findUserByUserName(userName, email)
    }

    suspend fun findByUserNamePassword(userName:String, password:String):User? {
        return userDao.findByUserNamePassword(userName, password)
    }

    suspend fun doRegister(user: User) {
        userDao.insert(user)
    }

}