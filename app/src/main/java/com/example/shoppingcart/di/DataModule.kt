package com.example.shoppingcart.di

import com.example.shoppingcart.data.DummyDataSource
import com.example.shoppingcart.data.Repository
import com.example.shoppingcart.model.DataBase
import org.koin.dsl.module

val dataModule = module {

    single { DataBase.getInstance() }
    factory { DummyDataSource() }
    single { Repository(get(), get()) }

}