package com.example.shoppingcart.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.data.Repository
import com.example.shoppingcart.model.product.ProductEntity
import com.example.shoppingcart.utill.ProductSavedType

class CartViewModel(val repository: Repository) : ViewModel() {

    private val _cartProduct = MutableLiveData<ArrayList<ProductEntity>>()
    val cartProduct: LiveData<ArrayList<ProductEntity>> = _cartProduct

    private var dataCart: ArrayList<ProductEntity> = ArrayList()

    fun loadDataCart() {
        dataCart.clear()
        dataCart.addAll(repository.getAllDb(ProductSavedType.CART))
        _cartProduct.postValue(dataCart)
    }

    fun removeProduct(productEntity: ProductEntity, cart: Int) {
        repository.removeProductCart(productEntity.id, ProductSavedType.CART)
    }

    fun addProduct(productEntity: ProductEntity) {
        repository.addToCart(productEntity)
    }

    fun subtractProduct(productEntity: ProductEntity) {
        repository.subtractCart(productEntity)
    }

}