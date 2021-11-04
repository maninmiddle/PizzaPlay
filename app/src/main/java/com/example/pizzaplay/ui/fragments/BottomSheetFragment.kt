package com.example.pizzaplay.ui.fragments

import CurrentLiveModel
import SenderLiveData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.example.pizzaplay.databinding.PizzaBottomSheetBinding
import com.example.pizzaplay.model.PizzaModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: PizzaBottomSheetBinding
    private val currentLiveModel: CurrentLiveModel by activityViewModels()
    private val senderLiveData: SenderLiveData by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PizzaBottomSheetBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.toggle.selectButton(btn = binding.btn1)

        currentLiveModel.pizzas.observe(activity as LifecycleOwner, {
            binding.pizzaName.text = it.name
            if (activity != null) {
                Glide.with(this).load(it.photo).into(binding.rivPizza)
            }
            binding.pizzaDesc.text = it.info
            binding.add.text = "В корзину ${it.price} "

        })

        binding.add.setOnClickListener {
            val pizza = PizzaModel(
                binding.pizzaName.text.toString(),
                binding.pizzaDesc.text.toString(),
                binding.rivPizza.drawable.toString(),
                binding.add.text.toString()
            )
            senderLiveData.pizzas.value = pizza
            dialog?.dismiss()
        }
    }

}