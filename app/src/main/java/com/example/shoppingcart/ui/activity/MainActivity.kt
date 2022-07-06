package com.example.shoppingcart.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppingcart.R
import com.example.shoppingcart.ui.cart.CartFragment
import com.example.shoppingcart.ui.shop.ShopFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shopFragment()
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        bottom_nav_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.shop -> shopFragment()
                R.id.cart -> cartFragment()
            }
            true
        }
    }

    private fun shopFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, ShopFragment()).commit()
    }

    private fun cartFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, CartFragment()).commit()
    }


}