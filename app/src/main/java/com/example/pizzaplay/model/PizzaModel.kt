package com.example.pizzaplay.model

data class PizzaModel(
    var name: String,
    var info: String,
    var photo: String,
    var price: String
) {
    var isAnimated: Boolean = false
}