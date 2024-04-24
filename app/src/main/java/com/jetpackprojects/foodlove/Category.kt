package com.jetpackprojects.foodlove

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
  val idCategory: String,
  val strCategory:String,
  val strCategoryThumb:String,
  val strCategoryDescription:String
): Parcelable //here we make the entire object as a string so it is easy to send between screens

data class CategoriesResponse(
  val categories:List<Category>
)