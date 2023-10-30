package com.example.funcy_portfolio_android.network

import com.example.funcy_portfolio_android.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class ServerConfig(
    val serverIP: String,
    val serverPort: Int
)

class ConnectFuncyApi {

    // サーバーのURLを取得するための関数
    // debug環境で動く
    private fun getServerURL(): String {
        val properties = readServerConfig()
        val serverIP: String = properties.serverIP
        val serverPort: Int = properties.serverPort

        return "http://$serverIP:$serverPort/"
    }

    private fun readServerConfig(): ServerConfig {
        return ServerConfig(BuildConfig.SERVER_IP, BuildConfig.SERVER_PORT)
    }

    private val logger =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    val gson = GsonBuilder()
        .serializeNulls()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(getServerURL())
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    object ApiService {
        val service: FuncyApi by lazy {
            ConnectFuncyApi().retrofit.create(FuncyApi::class.java)
        }
    }
}
