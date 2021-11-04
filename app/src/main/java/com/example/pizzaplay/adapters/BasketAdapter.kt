package com.example.pizzaplay.adapters

import CurrentLiveModel
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaplay.databinding.BasketLayoutBinding
import com.example.pizzaplay.model.PizzaModel

class BasketAdapter(
    private val context: Context,
    private val pizzas: List<PizzaModel>
) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    class ViewHolder(val binding: BasketLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            BasketLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BasketAdapter.ViewHolder, position: Int) {
        val data = CurrentLiveModel()
        val pizza = pizzas[position]

        holder.binding.example.text = pizza.name
    }

    override fun getItemCount() =
        pizzas.size
}