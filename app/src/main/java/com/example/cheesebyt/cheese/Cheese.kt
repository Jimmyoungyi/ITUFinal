package com.example.cheesebyt.cheese

data class Cheese (
    var cheeseID: String,
    var cheeseName: String,
    var cheeseImage: String,
    var cheeseRate: Float,
    var cheesePrice: Float,
    var cheeseDescription: String,
    var cheeseHowto: String,
    var wineParing: ArrayList<SubSlideItem>,
    var Recipes: ArrayList<SubSlideItem>,
    var reviews: ArrayList<ReviewListItem>,
    var substitute: ArrayList<SubSlideItem>
)