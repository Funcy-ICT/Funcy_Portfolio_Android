
import com.example.funcy_portfolio_android.ui.main.WorkData


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

//FuncyAPIサーバーの作品一覧取得URL
private const val SERVER_URL = "http://10.124.58.83:8080/"

//Moshiオブジェクトの作成
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Retrofitオブジェクトの作成
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
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
