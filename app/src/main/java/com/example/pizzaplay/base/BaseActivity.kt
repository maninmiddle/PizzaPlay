package com.example.pizzaplay.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pizzaplay.R

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
            .setCustomAnimations(R.anim.slideon, R.anim.slidein)
            .replace(id, fragment)
            .commit()
    }

}