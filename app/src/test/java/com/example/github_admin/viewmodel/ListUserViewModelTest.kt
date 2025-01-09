package com.example.github_admin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.github_admin.model.User
import com.example.github_admin.model.repository.GithubAdminRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userViewModel: ListUserViewModel

    @Mock
    private lateinit var userRepository: GithubAdminRepository

    private val testDispatcher = TestCoroutineDispatcher()

    private val mockUsers = listOf(
        User("www.google.com", "vietduong.cute", "Viet Duong", "hahaha", 1, "Kon Tum", 1000000, 1000000),
        User("www.google.com2", "vietduong.cute2", "Viet Duong 2", "hahaha2", 2, "Kon Tum", 1000000, 1000000)
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        userRepository = mock(GithubAdminRepository::class.java)
        userViewModel = ListUserViewModel(userRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `fetchUsers should update LiveData when repository returns data`() = runTest {
        `when`(userRepository.getListUser(perPage = 20, since = 0)).thenReturn(mockUsers)

        val observedValues = mutableListOf<List<User>>()

        val job = launch {
            userViewModel.listUser.collect { value ->
                observedValues.add(value)
            }
        }

        userViewModel.getListUser()
        advanceUntilIdle()
        assert(userViewModel.listUser.value == mockUsers)
        job.cancel()
    }

    @Test
    fun `fetchUsers should handle empty list from repository`() = runTest {
        val mockUsers = emptyList<User>()
        `when`(userRepository.getListUser(perPage = 20, since = 0)).thenReturn(mockUsers)
        val job = launch {
            userViewModel.listUser.collect { userList ->
                assertEquals(mockUsers, userList)
            }
        }
        userViewModel.getListUser()
        advanceUntilIdle()
        job.cancel()
    }

    @Test
    fun `fetchUsers should handle null response from repository`() = runTest {
        `when`(userRepository.getListUser(perPage = 20, since = 0)).thenReturn(null)
        val job = launch {
            userViewModel.listUser.collect { userList ->
                assertTrue(userList.isEmpty())
            }
        }

        userViewModel.getListUser()
        advanceUntilIdle()
        job.cancel()
    }
}