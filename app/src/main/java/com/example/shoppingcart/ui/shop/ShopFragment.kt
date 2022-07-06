package com.example.shoppingcart.ui.shop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.adapter.ExclusiveAdapter
import com.example.shoppingcart.listener.OnClickItemAndAdd
import com.example.shoppingcart.model.product.ProductEntity
import com.example.shoppingcart.ui.detailproduct.DetailProductActivity
import com.example.shoppingcart.utill.Constant
import com.example.shoppingcart.utill.ProductSavedType
import kotlinx.android.synthetic.main.fragment_shop.*
import org.koin.android.viewmodel.ext.android.viewModel


class ShopFragment : Fragment(R.layout.fragment_shop) {

    private val exclusiveAdapter: ExclusiveAdapter by lazy {
        ExclusiveAdapter()
    }

    private val viewModel: ShopViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager: LinearLayoutManager =
            GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        val myList = rv_exclusive_offer as RecyclerView
        myList.layoutManager = layoutManager
        setListExclusive()
        observeExclusiveOffer()
        viewModel.showDataExclusiveOffer()
        viewModel.showDataBestSelling()
        viewModel.showDataGroceries()

    }


    private fun observeExclusiveOffer() {
        viewModel.exclusiveOffer.observe(viewLifecycleOwner, {
            exclusiveAdapter.setDataAdapter(it)
        })
    }

    private fun setListExclusive() {
        rv_exclusive_offer.setHasFixedSize(true)
        rv_exclusive_offer.adapter = exclusiveAdapter
        exclusiveAdapter.onClickListener = object : OnClickItemAndAdd {
            override fun onClick(productEntity: ProductEntity) {
                toDetailExclusifeOffer(productEntity)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addProductToCart(productEntity, ProductSavedType.CART)
            }
        }
    }

    private fun toDetailExclusifeOffer(productEntity: ProductEntity) {
        val intent = Intent(activity, DetailProductActivity::class.java)
        intent.putExtra(Constant.DATA, productEntity)
        startActivity(intent)
    }

    private fun toDetailBestSelling(productEntity: ProductEntity) {
        val intent = Intent(activity, DetailProductActivity::class.java)
        intent.putExtra(Constant.DATA, productEntity)
        startActivity(intent)
    }

    private fun addProductToCart(productEntity: ProductEntity, cart: Int) {
        viewModel.addToCahar(productEntity, ProductSavedType.CART)
        Toast.makeText(activity, "Product added to cart", Toast.LENGTH_SHORT).show()
    }

}