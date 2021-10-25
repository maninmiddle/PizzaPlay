package com.example.pizzaplay.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pizzaplay.adapters.PizzasAdapter
import com.example.pizzaplay.api.Api
import com.example.pizzaplay.databinding.FragmentMenuBinding
import com.example.pizzaplay.model.LiveDataModel
import com.example.pizzaplay.model.PizzaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuFragment : Fragment() {
    lateinit var binding: FragmentMenuBinding
    private var pizzaList: MutableList<PizzaModel> = ArrayList()
    private val liveDataModel: LiveDataModel by activityViewModels()
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

            adapter = PizzasAdapter(context, pizzaList)
        }

    }

}