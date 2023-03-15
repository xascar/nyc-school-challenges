package dev.xascar.nycschools

import dev.xascar.nycschools.domain.Result
import dev.xascar.nycschools.network.OpenDataNetwork
import dev.xascar.nycschools.repo.SchoolsRepositoryModule
import dev.xascar.nycschools.ui.home.SchoolViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ViewModelTest {

    private lateinit var viewModel: SchoolViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testViewModel() = runTest {

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        try {
            viewModel = SchoolViewModel(
                SchoolsRepositoryModule.providesSchoolsRepository(
                    OpenDataNetwork.providesNYCOpenDataAPI(
                        OpenDataNetwork.providesRetrofit()
                    )
                )
            )

            viewModel.fetchSchools()
            print(viewModel.lState.value)
            assertEquals(Result.LOADING, viewModel.lState.value?.message)

        } finally {
            Dispatchers.resetMain()
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun settingMainDispatcher() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        try {
            viewModel = SchoolViewModel(
                SchoolsRepositoryModule.providesSchoolsRepository(
                    OpenDataNetwork.providesNYCOpenDataAPI(
                        OpenDataNetwork.providesRetrofit()
                    )
                )
            )
            viewModel.loadMessage() // Uses testDispatcher, runs its coroutine eagerly
            assertEquals("Greetings!", viewModel.message.value)
        } finally {
            Dispatchers.resetMain()
        }
    }
}