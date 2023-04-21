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

internal class GetSelectedSiteDomainInteractorTest {

    @Mock
    private lateinit var repository: MeliRepository

    private lateinit var interactor: GetSelectedSiteInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = GetSelectedSiteDomainInteractor(repository)
    }

    @Test
    fun `should return a Flow with the selected site when get selected site from data store`() : Unit = runBlocking{
        // given
        val site = mockk<SiteResponse>(relaxed = true)
        val flow: Flow<SiteResponse?> = flow {
            emit(site)
            delay(10)
            emit(SiteResponse("123", "MCO", "Colombia"))
        }

        Mockito.`when`(repository.getSelectedSite()).thenReturn(flow)

        // when
        val sites = interactor.execute()

        //then
        Mockito.verify(repository, Mockito.times(1)).getSelectedSite()
        Assert.assertEquals(site.name, sites.first()?.name)
        Assert.assertEquals(site.id, sites.first()?.id)
    }
}