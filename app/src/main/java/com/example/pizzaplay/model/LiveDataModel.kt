package com.example.pizzaplay.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class LiveDataModel : ViewModel() {
    val pizzas: MutableLiveData<List<PizzaModel>> by lazy {
        MutableLiveData<List<PizzaModel>>()
    }
}