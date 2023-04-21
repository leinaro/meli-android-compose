package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.MeliRepository
import com.leinaro.meli.data.repositories.ProductResponse
import io.mockk.every
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
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

internal class GetItemsDomainInteractorTest {
    @Mock
    private lateinit var repository: MeliRepository

    private lateinit var interactor: GetItemsInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = GetItemsDomainInteractor(repository)
    }

    @Test
    fun `should return a Flow with a list of products when fetching items by category successfully`(): Unit =
        runBlocking {
            // given
            val product = mockk<ProductResponse>(relaxed = true)
            every { product.currencyId }returns "COP"
            every { product.price } returns 15000.0

            val flow: Flow<List<ProductResponse>> = flow {
                emit(listOf(product))
                delay(10)
                emit(listOf())
            }

            `when`(repository.searchItems(null, null)).thenReturn(flow)

            // when
            val products = interactor.execute().first()

            //then
            Mockito.verify(repository, Mockito.times(1)).searchItems(null, null)
            Assert.assertEquals(product.title, products.first().name)
            Assert.assertEquals(product.id, products.first().id)
        }
}