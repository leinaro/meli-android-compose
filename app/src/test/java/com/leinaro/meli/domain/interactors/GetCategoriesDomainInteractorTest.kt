package com.leinaro.meli.domain.interactors

import com.leinaro.meli.data.repositories.CategoryResponse
import com.leinaro.meli.data.repositories.MeliRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

internal class GetCategoriesDomainInteractorTest {

    @Mock
    private lateinit var repository: MeliRepository

    private lateinit var interactor: GetCategoriesInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = GetCategoriesDomainInteractor(repository)
    }

    @Test
    fun `should return a Flow with a list of categories when fetching categories successfully`(): Unit = runBlocking{
        // given
        val flow: Flow<List<CategoryResponse>> = flow {
            emit(listOf(
                CategoryResponse("123","Cat")
            ))
            delay(10)
            emit(listOf())
        }

        `when`(repository.getCategories()).thenReturn(flow)

        // when
        val categories = interactor.execute().first()

        //then
        verify(repository, times(1)).getCategories()
        assertEquals("Cat",categories.first().name)
        assertEquals("123",categories.first().id)
    }
}