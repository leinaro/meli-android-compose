package com.leinaro.meli.data.remote

import com.leinaro.meli.data.repositories.CategoryDTO
import com.leinaro.meli.data.repositories.ProductResponse
import com.leinaro.meli.data.repositories.SiteResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeliServices {
    //https://api.mercadolibre.com
    @GET("sites")
    suspend fun getSites(): List<SiteResponse>

    //https://api.mercadolibre.com/sites/MLA/categories
    @GET("sites/{siteId}/categories")
    suspend fun getCategories(
        @Path("siteId") siteId: String,
        @Query("limit") limit: Int? = null,
    ): List<CategoryDTO>

    @GET("sites/{siteId}/search")
    suspend fun searchItems(
        @Path("siteId") siteId: String,
        @Query("category") categoryId: String? = null,
        @Query("q") query: String? = null,
        @Query("limit") limit: Int? = null,
    ): SearchItemResponse
}

/*
*
         "tags":[
            "supermarket_eligible",
            "immediate_payment",
            "cart_eligible",
            "best_seller_candidate",
            "shipping_guaranteed"
         ],
         "shipping":{
            "store_pick_up":false,
            "free_shipping":false,
            "logistic_type":"fulfillment",
            "mode":"me2",
            "tags":[
               "fulfillment",
               "loyalty_discount_eligible",
               "supermarket_eligible",
               "dragged_bids_and_visits",
               "good_quality_picture",
               "good_quality_thumbnail",
               "immediate_payment",
               "cart_eligible",
               "self_service_in"
            ],
            "promise":null
         },
         "stop_time":"2043-04-04T04:00:00.000Z",
         "seller":{
            "id":34541957,
            "nickname":"AQUAVIDA COLOMBIA",
            "car_dealer":false,
            "real_estate_agency":false,
            "_":false,
            "registration_date":"2010-10-04T11:38:38.000-04:00",
            "tags":[
               "normal",
               "developer",
               "eshop",
               "mshops",
               "messages_as_seller"
            ],
            "car_dealer_logo":"",
            "permalink":"http://perfil.mercadolibre.com.co/AQUAVIDA+COLOMBIA",
            "seller_reputation":{
               "level_id":"5_green",
               "power_seller_status":"platinum",
               "transactions":{
                  "canceled":434,
                  "completed":11386,
                  "period":"historic",
                  "ratings":{
                     "negative":0.04,
                     "neutral":0,
                     "positive":0.96
                  },
                  "total":11820
               },
               "metrics":{
                  "sales":{
                     "period":"60 days",
                     "completed":2325
                  },
                  "claims":{
                     "period":"60 days",
                     "rate":0.0091,
                     "value":22
                  },
                  "delayed_handling_time":{
                     "period":"60 days",
                     "rate":0.0004,
                     "value":1
                  },
                  "cancellations":{
                     "period":"60 days",
                     "rate":0.0078,
                     "value":19
                  }
               }
            },
            "eshop":{
               "eshop_id":161834,
               "seller":34541957,
               "nick_name":"AQUAVIDA COLOMBIA",
               "eshop_status_id":1,
               "site_id":"MCO",
               "eshop_experience":0,
               "eshop_rubro":null,
               "eshop_locations":[

               ],
               "eshop_logo_url":"http://http2.mlstatic.com/eshops-logo/34541957v15408a.png"
            }
         },
         "seller_address":{
            "comment":"",
            "address_line":"",
            "zip_code":"",
            "id":null,
            "latitude":null,
            "longitude":null,
            "country":{
               "id":"CO",
               "name":"Colombia"
            },
            "state":{
               "id":"CO-ANT",
               "name":"Antioquia"
            },
            "city":{
               "id":"TUNPQ01FRGRjNjc4",
               "name":"Medellín"
            }
         },
         "address":{
            "state_id":"CO-ANT",
            "state_name":"Antioquia",
            "city_id":"TUNPQ01FRGRjNjc4",
            "city_name":"Medellín"
         },
         "attributes":[
            {
               "id":"BRAND",
               "name":"Marca",
               "value_id":"3092523",
               "value_name":"Tetra",
               "attribute_group_id":"OTHERS",
               "attribute_group_name":"Otros",
               "value_struct":null,
               "values":[
                  {
                     "id":"3092523",
                     "name":"Tetra",
                     "struct":null,
                     "source":1
                  }
               ],
               "source":1,
               "value_type":"string"
            },
            {
               "id":"ITEM_CONDITION",
               "name":"Condición del ítem",
               "value_id":"2230284",
               "value_name":"Nuevo",
               "attribute_group_id":"OTHERS",
               "attribute_group_name":"Otros",
               "value_struct":null,
               "values":[
                  {
                     "id":"2230284",
                     "name":"Nuevo",
                     "struct":null,
                     "source":1
                  }
               ],
               "source":1,
               "value_type":"list"
            },
            {
               "id":"MODEL",
               "name":"Modelo",
               "value_id":"18012208",
               "value_name":"Tetra Color",
               "attribute_group_id":"OTHERS",
               "attribute_group_name":"Otros",
               "value_struct":null,
               "values":[
                  {
                     "id":"18012208",
                     "name":"Tetra Color",
                     "struct":null,
                     "source":1
                  }
               ],
               "source":1,
               "value_type":"string"
            },
            {
               "id":"PACKAGE_LENGTH",
               "name":"Largo del paquete",
               "value_id":null,
               "value_name":"8.8 cm",
               "attribute_group_id":"OTHERS",
               "attribute_group_name":"Otros",
               "value_struct":{
                  "number":8.8,
                  "unit":"cm"
               },
               "values":[
                  {
                     "id":null,
                     "name":"8.8 cm",
                     "struct":{
                        "number":8.8,
                        "unit":"cm"
                     },
                     "source":4333789534002961
                  }
               ],
               "source":4333789534002961,
               "value_type":"number_unit"
            },
            {
               "id":"PACKAGE_WEIGHT",
               "name":"Peso del paquete",
               "value_id":null,
               "value_name":"460 g",
               "attribute_group_id":"OTHERS",
               "attribute_group_name":"Otros",
               "value_struct":{
                  "number":460,
                  "unit":"g"
               },
               "values":[
                  {
                     "id":null,
                     "name":"460 g",
                     "struct":{
                        "number":460,
                        "unit":"g"
                     },
                     "source":4333789534002961
                  }
               ],
               "source":4333789534002961,
               "value_type":"number_unit"
            },
            {
               "id":"PRODUCT_CONSERVATION",
               "name":"Conservación del producto",
               "value_id":"10792872",
               "value_name":"Temperatura ambiente",
               "attribute_group_id":"OTHERS",
               "attribute_group_name":"Otros",
               "value_struct":null,
               "values":[
                  {
                     "id":"10792872",
                     "name":"Temperatura ambiente",
                     "struct":null,
                     "source":1
                  }
               ],
               "source":1,
               "value_type":"list"
            },
            {
               "id":"UNITS_PER_PACKAGE",
               "name":"Unidades por envase",
               "value_id":null,
               "value_name":"1",
               "attribute_group_id":"OTHERS",
               "attribute_group_name":"Otros",
               "value_struct":null,
               "values":[
                  {
                     "id":null,
                     "name":"1",
                     "struct":null,
                     "source":1505
                  }
               ],
               "source":1505,
               "value_type":"number"
            },
            {
               "id":"UNIT_WEIGHT",
               "name":"Peso de la unidad",
               "value_id":"3279840",
               "value_name":"375 g",
               "attribute_group_id":"OTHERS",
               "attribute_group_name":"Otros",
               "value_struct":{
                  "number":375,
                  "unit":"g"
               },
               "values":[
                  {
                     "id":"3279840",
                     "name":"375 g",
                     "struct":{
                        "unit":"g",
                        "number":375
                     },
                     "source":1
                  }
               ],
               "source":1,
               "value_type":"number_unit"
            }
         ],
         "installments":{
            "quantity":36,
            "amount":1525,
            "rate":0,
            "currency_id":"COP"
         },
         "winner_item_id":null,
         "catalog_listing":true,
         "discounts":null,
         "promotions":[

         ],
         "inventory_id":"CNGD17978"
      },*/
data class SearchItemResponse(
    //site_id
    //country_default_time_zone
    //paging
    val results: List<ProductResponse>,
)