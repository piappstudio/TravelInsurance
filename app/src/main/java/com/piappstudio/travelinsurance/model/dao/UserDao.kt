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

package com.piappstudio.travelinsurance.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piappstudio.travelinsurance.model.mbo.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE (userName=:userName OR email=:userName) AND password=:password")
    suspend fun findByUserNamePassword(userName:String, password:String):User?

    @Query("SELECT * FROM user WHERE userName=:userName OR email=:email")
    suspend fun findUserByUserName(userName: String, email:String):List<User>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}