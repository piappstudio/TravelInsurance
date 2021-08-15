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

package com.piappstudio.pilibrary.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.piappstudio.pilibrary.BuildConfig
import com.piappstudio.pilibrary.network.IPiDataSource
import com.piappstudio.pilibrary.network.PiLocalDataSource
import com.piappstudio.pilibrary.network.IPiRetrofitApi
import com.piappstudio.pilibrary.network.PiRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

private const val BASE_URL= "https://raw.githubusercontent.com/piappstudio/TravelInsurance/"

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    /** Used to create retrofit instance*/
    @Provides
    fun baseUrl() = BASE_URL

    @Singleton
    @Provides
    /** To create [OkHttpClient],Used to create retrofit instance. */
    fun provideOkHttp():OkHttpClient {
        return if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        } else {
            OkHttpClient.Builder().build()
        }
    }

    /** To create [Gson] object, used to create retrofit instance */
    @Singleton
    @Provides
    fun providesGson() = GsonBuilder().setLenient().create()

    /** To create retrofit object based on [okHttpClient] [baseUrl] and [gson] configuration */
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl:String, gson:Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(baseUrl)
        .client(okHttpClient).build()


    /** Tell to Hilt, how to construct [IPiRetrofitApi] object */
    @Provides
    fun provideTIApiDetail(retrofit: Retrofit): IPiRetrofitApi = retrofit.create(IPiRetrofitApi::class.java)


    /** Here there 2 class that implement [IPiDataSource] interface */

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RemoteAccess

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LocalAccess

    @RemoteAccess
    @Provides
    fun provideRemoteImp(imp: PiRemoteDataSource):IPiDataSource = imp

    @LocalAccess
    @Provides
    fun provideLocalImp(imp: PiLocalDataSource):IPiDataSource = imp
}
