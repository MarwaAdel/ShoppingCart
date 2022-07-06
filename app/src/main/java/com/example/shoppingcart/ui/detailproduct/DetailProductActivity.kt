package com.example.shoppingcart.ui.detailproduct

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.shoppingcart.R
import com.example.shoppingcart.model.product.ProductEntity
import com.example.shoppingcart.utill.Constant
import com.example.shoppingcart.utill.ProductSavedType
import kotlinx.android.synthetic.main.activity_detail_product.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class DetailProductActivity : AppCompatActivity() {

    private val viewModel: DetailProductViewModel by viewModel()

    private val product by lazy {
        intent.getParcelableExtra<ProductEntity>(Constant.DATA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        loadDataDetail(product!!)
        addProductToCart(product!!, ProductSavedType.CART)

        viewModel.checkProduct(product!!)
        viewModel.loadDataDetail()
    }


    @SuppressLint("SetTextI18n")
    private fun loadDataDetail(productEntity: ProductEntity) {
        val price = productEntity.price
        val dec = DecimalFormat("#,###")
        val priceRupiah = dec.format(price)

        Glide.with(this).load(productEntity.picture)
            .transition(DrawableTransitionOptions.withCrossFade()).into(iv_picture_detail)
        tv_name_detail.text = productEntity.name
        tv_description_detail.text = productEntity.description
        tv_price_detail.text = "IDR $priceRupiah"
    }


    private fun addProductToCart(productEntity: ProductEntity, cart: Int) {
        btn_detail_addtocart.setOnClickListener {
                viewModel.addToCahar(productEntity, ProductSavedType.CART)
                Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show()
        }
    }

}