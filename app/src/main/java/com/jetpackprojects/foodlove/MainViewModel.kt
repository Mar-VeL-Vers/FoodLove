package com.jetpackprojects.foodlove

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// COMMUNICATION BETWEEN DATA AND UI!!!

class MainViewModel : ViewModel() {

  private val _categorieState = mutableStateOf(RecipeState())

  val categoriesState: State<RecipeState> =
    _categorieState //this is the variable that will be shown to other classes

  //we fetch the categories on initialization
  init {
    fetchCategories()
  }


  private fun fetchCategories() {
    /**a ViewModel scope provides a launch for suspend functions to be processed.
     * A suspend function is something that runs in the background.
    it's basically really taking care of doing things in the background.
     * if you want to start a suspend function, you actually have to start it inside of a coroutine*
     * in order for this to happen we can use this thing called view model scope.*/
    viewModelScope.launch {
      try {
        val response = recipeService.getCategories()
        _categorieState.value = _categorieState.value.copy(
          list = response.categories,
          loading = false,
          error = null
        )

      } catch (e: Exception) {
        _categorieState.value = _categorieState.value.copy(
          loading = false,
          error = "Error fetching Categories ${e.message}"
        )
      }
    }
  }


  /** we need to know whether we have the recipes or not and at which state of basically the recipe downloading
  we are are we loading or are we not loading?
  Do we have a list or do we not have a list?
  Do we have an error or do we not have an error?
  So basically we're going to create a data class for just that.
  And that will be our recipe state.**/
  data class RecipeState(
    val loading: Boolean = true,
    val list: List<Category> = emptyList(),
    val error: String? = null
  )
}