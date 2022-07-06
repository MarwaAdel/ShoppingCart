package com.example.shoppingcart.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shoppingcart.R
import com.example.shoppingcart.adapter.CartAdapter
import com.example.shoppingcart.listener.OnClickItemAddRemove
import com.example.shoppingcart.listener.OnTotalChange
import com.example.shoppingcart.model.product.ProductEntity
import com.example.shoppingcart.ui.detailproduct.DetailProductActivity
import com.example.shoppingcart.utill.Constant
import com.example.shoppingcart.utill.ProductSavedType
import kotlinx.android.synthetic.main.fragment_cart.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat


class CartFragment : Fragment(R.layout.fragment_cart) {

    private val viewModel: CartViewModel by viewModel()

    private val cartAdapter: CartAdapter by lazy {
        CartAdapter(object : OnTotalChange {
            override fun onTotalChange(total: Int) {
                val dec = DecimalFormat("#,###")
                val priceRupiah = dec.format(total)

                tv_total_price.text = "IDR $priceRupiah"
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeCart()
        setListCart()
        viewModel.loadDataCart()

    }

    private fun observeCart() {
        viewModel.cartProduct.observe(viewLifecycleOwner, {
            cartAdapter.setDataAdapter(it)
        })
    }

    private fun setListCart() {

        rv_cart.setHasFixedSize(true)
        rv_cart.adapter = cartAdapter
        cartAdapter.onClickListener = object : OnClickItemAddRemove {
            override fun onClick(productEntity: ProductEntity) {
                val intent = Intent(activity, DetailProductActivity::class.java)
                intent.putExtra(Constant.DATA, productEntity)
                startActivity(intent)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addQtyProduct(productEntity)
            }

            override fun onClickSubstract(productEntity: ProductEntity) {
                substractQtyProduct(productEntity)
            }

            override fun onClickRemove(productEntity: ProductEntity) {
                removeFromCart(productEntity)
            }
        }

    }

    private fun addQtyProduct(productEntity: ProductEntity) {
        viewModel.addProduct(productEntity)
        viewModel.loadDataCart()
    }

    private fun substractQtyProduct(productEntity: ProductEntity) {
        viewModel.subtractProduct(productEntity)
        viewModel.loadDataCart()
    }

    private fun removeFromCart(productEntity: ProductEntity) {
        viewModel.removeProduct(productEntity, ProductSavedType.CART)
        Toast.makeText(activity, "Product removed from cart", Toast.LENGTH_SHORT).show()
        viewModel.loadDataCart()
    }


}