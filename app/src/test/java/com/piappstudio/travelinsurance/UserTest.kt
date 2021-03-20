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

package com.piappstudio.travelinsurance

import com.piappstudio.travelinsurance.model.mbo.User
import com.piappstudio.travelinsurance.util.toSHA256Hash
import junit.framework.TestCase
import org.junit.Test

class UserTest : TestCase() {

    @Test
    fun testUser() {
        val user = User( userName = "piappstudio", password = "123456")
        assertEquals("piappstudio", user.userName)
        assertEquals("123456", user.password)
        val hash = user.password.toSHA256Hash()
        val hash2 = user.password.toSHA256Hash()
        assert(hash != hash2)
    }

    fun testSecondaryConstructor() {
        val user = User(firstName = "Pi", lastName = "App", mobileNumber = "4156154486",
                userName = "piappstudio", password = "piappstudio@gmail.com", email = "123456")
        assertEquals("Pi", user.firstName)
        assertEquals("App", user.lastName)
        assertEquals("4256154486", user.mobileNumber)

    }
}