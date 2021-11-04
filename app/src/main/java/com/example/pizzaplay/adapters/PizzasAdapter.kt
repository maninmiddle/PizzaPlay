package com.example.pizzaplay.adapters

import CurrentLiveModel
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizzaplay.animator.ViewAnimator
import com.example.pizzaplay.databinding.PizzaLayoutBinding
import com.example.pizzaplay.model.LiveDataModel
import com.example.pizzaplay.model.PizzaModel
import com.example.pizzaplay.ui.fragments.BottomSheetFragment


class PizzasAdapter(
    private val context: Context,
    private val pizzas: List<PizzaModel>,
    private val liveDataModel: CurrentLiveModel
) : RecyclerView.Adapter<PizzasAdapter.ViewHolder>() {

    class ViewHolder(val binding: PizzaLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            PizzaLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = CurrentLiveModel()
        val pizza = pizzas[position]

        if (!pizza.isAnimated) {
            if (position % 2 == 0)
                ViewAnimator(holder.binding.root)
                    .translationX(1000, -100f, 0f)
            else
                ViewAnimator(holder.binding.root)
                    .translationX(1000, 100f, 0f)


            pizza.isAnimated = true
        }

        holder.binding.pizzaName.text = pizza.name
        holder.binding.pizzaDesc.text = pizza.info
        holder.binding.button.text = pizza.price
        Glide.with(context).load(pizza.photo).into(holder.binding.pizzaImage)

        holder.binding.button.setOnClickListener {
            liveDataModel.pizzas.value = pizza
            val bottomSheetFragment = BottomSheetFragment()
            val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            bottomSheetFragment.show(manager, "BottomSheetDialog")
        }
    }


    override fun getItemCount() = pizzas.size
}