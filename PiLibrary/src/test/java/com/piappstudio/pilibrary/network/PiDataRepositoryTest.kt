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

package com.piappstudio.pilibrary.network

import android.content.Context
import com.piappstudio.pilibrary.common.isFileExist
import com.piappstudio.pilibrary.common.isNetworkAvailable
import com.piappstudio.pilibrary.common.saveFileOnCache
import com.piappstudio.pilibrary.di.NetworkModule.provideOkHttp
import com.piappstudio.pilibrary.di.NetworkModule.provideRetrofit
import com.piappstudio.pilibrary.di.NetworkModule.provideTIApiDetail
import com.piappstudio.pilibrary.di.NetworkModule.providesGson
import com.piappstudio.pilibrary.utility.Resource
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File

const val JSON_PATH = "../service/"
class PiDataRepositoryTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var piDataRepository: PiDataRepository

    @Before
    fun setUp() {

        val context = mockk<Context>()
        mockkStatic("com.piappstudio.pilibrary.common.CommonUtilKt")
        every { context.isNetworkAvailable() } returns true
        every { context.isFileExist(any()) } returns false
        every { context.saveFileOnCache(any(),any()) } returns Unit

        // Set up mock server
        mockWebServer = MockWebServer()
        mockWebServer.start(9901)
        mockWebServer.url("/")
        // end of mock server configuration

        val baseUrl = "http://localhost:9901/"
        val localDataSource = mockk<PiLocalDataSource>(relaxed = true)
        val retrofit = provideRetrofit(provideOkHttp(), baseUrl, providesGson())
        val remoteDataSource = PiRemoteDataSource(provideTIApiDetail(retrofit))
        piDataRepository = PiDataRepository(context, localDataSource, remoteDataSource)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun loadFile(path:String):String {
        return File(path).readText()
    }

    @Test
    fun `test load insurance`() {
        //load mock response to the server
        val insuranceJson = loadFile(JSON_PATH+"insurance.json")
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(insuranceJson))

        runBlocking {
            val lstFlows = piDataRepository.fetchInsurances().take(2).toList()
            assertEquals(Resource.Status.LOADING, lstFlows[0].status)
            assertEquals(Resource.Status.SUCCESS, lstFlows[1].status)
            assertTrue(lstFlows[1].data?.isNotEmpty() == true)
        }
    }

    @Test
    fun `test load insurance with server down scenario`() {
        //load mock response to the server
        mockWebServer.enqueue(MockResponse().setResponseCode(500).setBody("Server down"))
        runBlocking {
            val lstFlows = piDataRepository.fetchInsurances().take(2).toList()
            assertEquals(Resource.Status.LOADING, lstFlows[0].status)
            assertEquals(Resource.Status.ERROR, lstFlows[1].status)
            assertEquals(500, lstFlows[1].error?.code)
        }
    }

    @Test
    fun `test load insurance with 404 scenario`() {
        //load mock response to the server
        mockWebServer.enqueue(MockResponse().setResponseCode(404).setBody("Url not found"))
        runBlocking {
            val lstFlows = piDataRepository.fetchInsurances().take(2).toList()
            assertEquals(Resource.Status.LOADING, lstFlows[0].status)
            assertEquals(Resource.Status.ERROR, lstFlows[1].status)
            assertEquals(404, lstFlows[1].error?.code)
        }
    }
}