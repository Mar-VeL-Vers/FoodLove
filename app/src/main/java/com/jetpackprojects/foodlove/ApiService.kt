package com.jetpackprojects.foodlove

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**very important code witch builds a connection to the base url with a format that can convert
 * whatever it gets to kotlin objets
 *  this retrofit builder is responsible for preparing the endpoint and adding the Json converter.
 *
 * It then provides the create method.
 *
 * For gaining access to the service methods.(we can set up multiple services depending on our needs)
 *
 * So for example our Get categories.
 **/
private val retrofit = Retrofit.Builder()
  .baseUrl("https://www.themealdb.com/api/json/v1/1/")
  .addConverterFactory(
    GsonConverterFactory
      .create()
  )
  .build()

/** after we build the object, we can create it using an API service**/
val recipeService= retrofit.create(ApiService::class.java)

interface ApiService {
  //@get->does a http request
  /** categories.php. comes from free meal api :www.themealdb.com/api/json/v1/1/categories.php
   * we are intersted just to the end point of the link to take this file**/

  @GET("categories.php")
  suspend fun getCategories(): CategoriesResponse
}