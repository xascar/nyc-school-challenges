package dev.xascar.nycschools


import kotlinx.coroutines.*
import org.junit.Test
import java.net.URL

class CustomizedNetworkClientTest {

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Test
    fun test() {
        runBlocking {
            val url = URL("https://data.cityofnewyork.us/resource/s3k6-pzi2.json")
            val json = url.readText()
            print(json)
        }
    }
}
//
//class OpenDataAPI(networkClient: NetworkClient = URLSession.shared) {
//
//    private val networkClient: NetworkClient
//
//    init {
//        this.networkClient = networkClient
//    }
//
//    suspend fun highSchools(): List<HighSchoolResponse> {
//        fetchAndDecodeData(HighSchoolResponse.serializer().list, "s3k6-pzi2")
//    }
//
//    suspend fun satScores(): List<SATScores> {
//        fetchAndDecodeData(SATScores.serializer().list, "f9bf-2cp4")
//    }
//
//    private suspend fun <D> fetchAndDecodeData(serializer: SerializationStrategy<D>, name: String): D {
//        val baseURL = URL("https://data.cityofnewyork.us/resource")
//        val url = baseURL.append(path = name).appendPathExtension("json")
//        val (data, _) = networkClient.data(from = url)
//        val json = Json(JsonConfiguration.Stable)
//        return json.parse(serializer, data.toString(Charsets.UTF_8))
//    }
//}
//
//
//class CustomizedNetworkClientTest {
//}