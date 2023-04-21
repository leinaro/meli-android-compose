package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.MeliRepository
import com.leinaro.meli.data.repositories.SiteResponse
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class GetSitesDomainInteractorTest {

    @Mock
    private lateinit var repository: MeliRepository

    private lateinit var interactor: GetSitesInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = GetSitesDomainInteractor(repository)
    }

    @Test
    fun `should return a Flow with a list of categories when fetching categories successfully`(): Unit =
        runBlocking {
            // given
            val site = mockk<SiteResponse>(relaxed = true)
            val flow: Flow<List<SiteResponse>> = flow {
                emit(listOf(site))
                delay(10)
                emit(listOf())
            }

            Mockito.`when`(repository.getSites()).thenReturn(flow)

            // when
            val sites = interactor.execute().first()

            //then
            Mockito.verify(repository, Mockito.times(1)).getSites()
            Assert.assertEquals(site.name, sites.first().name)
            Assert.assertEquals(site.id, sites.first().id)
        }
}