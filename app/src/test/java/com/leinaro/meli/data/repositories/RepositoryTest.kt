package com.leinaro.meli.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leinaro.meli.data.local.UserDataStore
import com.leinaro.meli.data.remote.MeliServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
internal class RepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var meliServices: MeliServices

    @Mock
    private lateinit var userDataStore: UserDataStore

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        repository = Repository(
            userDataStore = userDataStore,
            meliServices = meliServices,
        )
    }

    @Test
    fun `should return a Flow with a list of categories when fetching categories successfully`(): Unit =
        runBlocking {
            // given
            val categoriesResponse: List<CategoryResponse> = listOf(
                CategoryResponse("123", "Cat")
            )
            val selectedId = flowOf("MCO")

            Mockito.`when`(userDataStore.getSelectedSiteId()).thenReturn(selectedId)
            //Mockito.`when`(meliServices.getCategories("MCO")).thenReturn(categoriesResponse)

            // when
            repository.getCategories()
                //then
                Mockito.verify(userDataStore, Mockito.times(1)).getSelectedSiteId()
               // Mockito.verify(meliServices, Mockito.times(1)).getCategories("MCO")
                //  Assert.assertEquals("Cat", categories.first().name)
                //  Assert.assertEquals("123", categories.first().id)

          }

    @Test
    fun `should return a Flow with a list of sites when fetching sites successfully`(): Unit =
        runBlocking {
            // given
            val sitesResponse: List<SiteResponse> = listOf(
                SiteResponse("COP", "MCO", "Colombia")
            )
            Mockito.`when`(meliServices.getSites()).thenReturn(sitesResponse)

            // when
            repository.getSites().collect {

                //then
                Mockito.verify(meliServices, Mockito.times(1)).getSites()
                //  Assert.assertEquals("Cat", categories.first().name)
                //  Assert.assertEquals("123", categories.first().id)
            }
        }

    @Test
    fun `should return a Flow with a selected flow when fetching selected site successfully`(): Unit =
        runBlocking {
            // given
            val selectedSiteFLow = flow {
                emit("MCO")
            }
            Mockito.`when`(userDataStore.getSelectedSiteId()).thenReturn(selectedSiteFLow)

            // when
            repository.getSelectedSite()

            //then
            Mockito.verify(userDataStore, Mockito.times(1)).getSelectedSiteId()
            //  Assert.assertEquals("Cat", categories.first().name)
            //  Assert.assertEquals("123", categories.first().id)
        }

    @Test
    fun `should return a Flow with a list of items flow when searching items successfully`(): Unit =
        runBlocking {
            // given
            val selectedSiteFLow = flow {
                emit("MCO")
            }
            Mockito.`when`(userDataStore.getSelectedSiteId()).thenReturn(selectedSiteFLow)

            // when
            repository.searchItems("MCO213", "ps5")

            //then
            Mockito.verify(userDataStore, Mockito.times(1)).getSelectedSiteId()
            //  Assert.assertEquals("Cat", categories.first().name)
            //  Assert.assertEquals("123", categories.first().id)
        }

    @Test
    fun `should update successfully the selected site when set selected site is successfully`(): Unit =
        runBlocking {
            // given
            val selectedSiteFLow = flow {
                emit("MCO")
            }
            Mockito.`when`(userDataStore.setSelectedSite("MCO123")).thenReturn(Unit)

            // when
            repository.setSite("MCO213")

            //then
            Mockito.verify(userDataStore, Mockito.times(1)).getSelectedSiteId()
            //  Assert.assertEquals("Cat", categories.first().name)
            //  Assert.assertEquals("123", categories.first().id)
        }
}