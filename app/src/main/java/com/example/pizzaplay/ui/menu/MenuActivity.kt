package com.example.pizzaplay.ui.menu

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pizzaplay.R
import com.example.pizzaplay.api.Api
import com.example.pizzaplay.base.BaseActivity
import com.example.pizzaplay.databinding.ActivityMenuBinding
import com.example.pizzaplay.model.LiveDataModel
import com.example.pizzaplay.model.PizzaModel
import com.example.pizzaplay.ui.fragments.BasketFragment
import com.example.pizzaplay.ui.fragments.CategoryFragment
import com.example.pizzaplay.ui.fragments.MenuFragment
import com.example.pizzaplay.ui.fragments.UserFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuActivity : BaseActivity() {
    private lateinit var binding: ActivityMenuBinding
    private val liveDataModel: LiveDataModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeApiRequest()

        binding.bottomNav.itemIconTintList = null

        val menuFragment = MenuFragment()
        val categoryFragment = CategoryFragment()
        val basketFragment = BasketFragment()
        val userFragment = UserFragment()

        startFragment(menuFragment, binding.framed.id)

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mn_menu -> startFragment(menuFragment, binding.framed.id)
                R.id.mn_category -> startFragment(categoryFragment, binding.framed.id)
                R.id.mn_basket -> startFragment(basketFragment, binding.framed.id)
                R.id.mn_user -> startFragment(userFragment, binding.framed.id)
            }
            true
        }
    }

    private fun makeApiRequest() {
        Api.apiMethods
            .getPizza()
            .enqueue(object : Callback<List<PizzaModel>> {
                override fun onResponse(
                    call: Call<List<PizzaModel>>,
                    response: Response<List<PizzaModel>>
                ) {
                    if (response.body() != null ) {
                        liveDataModel.pizzas.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<PizzaModel>>, t: Throwable) {
                    Log.e("sdasadf", "error")
                }

            })
    }
}