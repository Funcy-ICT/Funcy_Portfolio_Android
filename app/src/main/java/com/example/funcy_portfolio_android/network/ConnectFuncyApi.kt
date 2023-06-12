
import com.example.funcy_portfolio_android.model.WorkData
import com.google.gson.GsonBuilder


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

//FuncyAPIサーバーの作品一覧取得URL
private const val SERVER_URL = "http://10.124.56.217:9000/"

//gsonオブジェクトの作成
private val gson = GsonConverterFactory.create(GsonBuilder().serializeNulls().create())

//Retrofitオブジェクトの作成
private val retrofit = Retrofit.Builder()
    .addConverterFactory(gson)
    .baseUrl(SERVER_URL)
    .build()

//FuncyAPIサーバーとの通信方法を定義
interface ConnectFuncyApi{
    @GET("works/10") // 取得する作品数
    suspend fun getWorks(
        @Header("token") token:String
    ):List<WorkData>
}

object FuncyApi{
    val retrofitService: ConnectFuncyApi by lazy {retrofit.create(ConnectFuncyApi::class.java)}
}
