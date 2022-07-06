package com.example.shoppingcart.di

import com.example.shoppingcart.ui.cart.CartViewModel
import com.example.shoppingcart.ui.detailproduct.DetailProductViewModel
import com.example.shoppingcart.ui.product.ProductViewModel
import com.example.shoppingcart.ui.shop.ShopViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ShopViewModel(get()) }
    viewModel { ProductViewModel(get()) }
    viewModel { DetailProductViewModel(get()) }
    viewModel { CartViewModel(get()) }
}