package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.MeliRepository
import com.leinaro.meli.domain.entities.Site
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class SelectSiteDomainInteractorTest {

    @Mock
    private lateinit var repository: MeliRepository

    private lateinit var interactor: SelectSiteInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = SelectSiteDomainInteractor(repository)
    }

    @Test
    fun `should update data store selected site id when set selected site is called`(): Unit =
        runBlocking {
            // given
            val site = Site("", "")
            Mockito.`when`(repository.setSite("123")).thenReturn(Unit)

            // when
            interactor.execute(site)

            //then
            Mockito.verify(repository, Mockito.times(1)).setSite(site.id)
        }
}