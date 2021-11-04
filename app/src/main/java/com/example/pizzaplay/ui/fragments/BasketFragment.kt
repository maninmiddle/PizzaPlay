package com.example.pizzaplay.ui.fragments

import SenderLiveData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzaplay.adapters.BasketAdapter
import com.example.pizzaplay.databinding.FragmentBasketBinding
import com.example.pizzaplay.model.PizzaModel


class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private var pizzaList: MutableList<PizzaModel> = ArrayList()
    private val sender: SenderLiveData by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sender.pizzas.observe(activity as LifecycleOwner, {
            pizzaList.remove(it)
            pizzaList.add(it)
            binding.rvLayout.adapter?.notifyItemInserted(0)
        })

        binding.rvLayout.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = BasketAdapter(context, pizzaList)
        }

    }

}