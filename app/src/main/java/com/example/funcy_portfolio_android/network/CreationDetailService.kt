
private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.124.58.83:8080/")
    .addConverterFactory(gson)
    .build()

object CreationDetailNetwork{
    val creationDetail: CreationDetailService by lazy{
        retrofit.create(CreationDetailService::class.java)
    }
}