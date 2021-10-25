package com.example.pizzaplay.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity() {
    fun startActivity(activityClass: Class<*>) {
        startActivity(
            Intent(
                applicationContext,
                activityClass
            )
        )
    }

    fun startActivityWithFinish(activityClass: Class<*>, affinity: Boolean) {
        startActivity(
            Intent(
                applicationContext,
                activityClass
            )
        )
        if (affinity)
            finishAffinity()
        else
            finish()
    }

    fun startFragment(fragment: Fragment, id: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(id, fragment)
            .commit()
    }

}