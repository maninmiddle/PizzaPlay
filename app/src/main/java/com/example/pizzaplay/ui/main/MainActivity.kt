package com.example.pizzaplay.ui.main

import android.os.Bundle
import android.widget.Toast
import com.example.pizzaplay.R
import com.example.pizzaplay.api.Api
import com.example.pizzaplay.base.BaseActivity
import com.example.pizzaplay.databinding.ActivityMainBinding
import com.example.pizzaplay.model.UserModel
import com.example.pizzaplay.ui.menu.MenuActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        checkAuth()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.doneAuth.setOnClickListener {
            Api.apiMethods.getUserByUsername(binding.etUsername.text.toString())
                .enqueue(object : Callback<UserModel> {
                    override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                        when {
                            response.body()?.password.isNullOrEmpty() -> createAccount()
                            response.body()?.password == binding.etPassword.text.toString() -> loginAccount()
                            response.body()?.password != binding.etPassword.text.toString() -> passwordPlug()
                        }
                    }


                    private fun passwordPlug() {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.invalid_password),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    private fun loginAccount() {
                        val username = binding.etUsername.text
                        val password = binding.etPassword.text

                        when {
                            username.isNullOrEmpty() -> binding.etUsername.error =
                                getString(R.string.typeUser)
                            password.isNullOrEmpty() -> binding.etPassword.error =
                                getString(R.string.typePassword)
                            else -> {
                                startActivityWithFinish(MenuActivity::class.java, false)
                                getSharedPreferences("data", MODE_PRIVATE)
                                    .edit()
                                    .putString("username", binding.etUsername.text.toString())
                                    .apply()
                            }
                        }
                    }

                    private fun createAccount() {
                        val username = binding.etUsername.text
                        val password = binding.etPassword.text

                        when {
                            username.isNullOrEmpty() -> binding.etUsername.error =
                                getString(R.string.typeUser)
                            password.isNullOrEmpty() -> binding.etPassword.error =
                                getString(R.string.typePassword)
                            else -> {
                                val userInfo = UserModel(
                                    binding.etUsername.text.toString(),
                                    binding.etPassword.text.toString(),
                                    1
                                )

                                getSharedPreferences("data", MODE_PRIVATE)
                                    .edit()
                                    .putString("username", binding.etUsername.text.toString())
                                    .apply()

                                Api.apiMethods.createUser(userInfo)
                                    .enqueue(object : Callback<String> {
                                        override fun onResponse(
                                            call: Call<String>,
                                            response: Response<String>
                                        ) {
                                            startActivityWithFinish(MenuActivity::class.java, false)
                                        }

                                        override fun onFailure(call: Call<String>, t: Throwable) {
                                            t.printStackTrace()
                                        }

                                    })
                            }
                        }


                    }

                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        t.printStackTrace()
                    }

                })
        }
    }

    private fun checkAuth() {
        val authUser = getSharedPreferences("data", MODE_PRIVATE)
            .getString("username", "")

        if (authUser != null) {
            if (authUser.isNotEmpty()) {
                startActivityWithFinish(MenuActivity::class.java, false)
            }
        }
    }
}