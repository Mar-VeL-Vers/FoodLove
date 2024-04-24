package com.jetpackprojects.foodlove

//takes care of individual routes for navigation

sealed class Screen(val route:String) {
  object RecipeScreen:Screen("recipescreen")
  object DetailScreen:Screen("detailscreen")


}