package dev.xascar.nycschools

import dev.xascar.nycschools.network.OpenDataNetwork
import dev.xascar.nycschools.repo.SchoolsRepository
import dev.xascar.nycschools.repo.SchoolsRepositoryModule
import org.junit.Before
import org.junit.Test

class NYCOpenDataTest {
    //test repository
    private lateinit var repository: SchoolsRepository

    @Before
    fun setUp() {
        repository = SchoolsRepositoryModule.providesSchoolsRepository(
            OpenDataNetwork.providesNYCOpenDataAPI(
                OpenDataNetwork.providesRetrofit()
            )
        )
    }
    @Test
    fun testRepository() {
//        repository.getSchoolModel(object : SchoolsRepository.SchoolsCallback {
//
//
//            override fun onSuccess(schools: List<SchoolModel>) {
//                assert(true)
//            }
//
//            override fun onFailure(e: Throwable) {
//                assert(false)
//            }
//        })
    }


}