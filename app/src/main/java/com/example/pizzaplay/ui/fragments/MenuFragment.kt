package com.example.pizzaplay.ui.fragments

import CurrentLiveModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pizzaplay.adapters.PizzasAdapter
import com.example.pizzaplay.databinding.FragmentMenuBinding
import com.example.pizzaplay.model.LiveDataModel
import com.example.pizzaplay.model.PizzaModel

class MenuFragment : Fragment() {
    lateinit var binding: FragmentMenuBinding
    private var pizzaList: MutableList<PizzaModel> = ArrayList()
    private val liveDataModel: LiveDataModel by activityViewModels()
    private val currentLiveModel: CurrentLiveModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        liveDataModel.pizzas.observe(activity as LifecycleOwner, {
            pizzaList.removeAll(it)
            pizzaList.addAll(it)
            binding.rvLayout.adapter?.notifyItemInserted(0)
        })

        binding.rvLayout.apply {
            layoutManager = GridLayoutManager(context, 2)

            adapter = PizzasAdapter(context, pizzaList, currentLiveModel)
        }

    }

}