package com.jetpackprojects.foodlove

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
  val recipeViewModel: MainViewModel = viewModel()
  val viewstate by recipeViewModel.categoriesState

  NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
    composable(route = Screen.RecipeScreen.route) {
      RecipeScreen(
        viewState = viewstate,
        navigateToDetail = {
          //here we are passing data from the current screen to detail screen
          navController
            .currentBackStackEntry
            ?.savedStateHandle//here we save the object that we serialized with parcelable
            ?.set("cat", it)//here we serialize, send an entire object
          navController.navigate(Screen.DetailScreen.route)
        })
    }
    composable(route = Screen.DetailScreen.route) {
      val category = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<Category>("cat") ?: Category("", "", "", "")

      CategoryDetailScreen(category = category)
    }
  }
}