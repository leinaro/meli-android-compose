# meli-example

This app is and example of how to use compose, flows, data store, viewmodel and mercadolibre sample services https://developers.mercadolibre.com.co/

The application show a list of products, a product details, allow user to select country and search products.

## Bases

Developed in kotlin, use Oriented Object Programing, clean architecture and good practices.  

## Architecture

The used architecture is Clean Architecture, I organize the classes in three folders that could be modules in the future:
- ui
- domain
- data

You can see more details about architecture in [recommended-app-architecture](https://developer.android.con/jetpack/guide?#recommended-app-arch) and in the blog [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

El proyecto esta organizado de la manera que en cada actividad se usa un navigation component donde se crea el viewmodel correspondiente, hay diferentes componentes *Screen que representa las diferente pantallas de la aplicación.
Cada viewmodel tiene una dependencia con uno o varios casos de uso, los casos de uso tiene una dependencia con otros casos de uso y/o el repositorio.
el repositorio tiene dependencias con el cliente remoto y cliente local.

La estructura de carpetas es la siguiente:
- presentation // activities, composable components y viewmodels
- domain // interactors o use cases 
- data // respository y data sources

## Mercadolibre Services

Podrás ver la implementacion de los siguientes endpoing

- Obtener todas las categories para Colombia(MCO)
GET https://api.mercadolibre.com/sites/MCO/categories"
- Obtener los productos por categoria
GET https://api.mercadolibre.com/sites/MCO/search?category={categoryId}"
- Obtener el detalle de un producto incluidas las imagenes
GET https://api.mercadolibre.com/items/{productId}"
- Obtener la description de un producto
GET https://api.mercadolibre.com/tems/{productId}/description"

## Design Patterns
- Creacionales: Dependency Injection, Singleton,
- Estructurales: MVVM
- Comportamiento: Observer

## Android Architecture Components
- LiveData
- ViewModel

## Jetpack
- Couroutines + Flow
- Navigation

## Other Dependencies
- Hilt
- Coil
- Mockito
- Retrofit2
- Okhttp
