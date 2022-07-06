package com.example.shoppingcart.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppingcart.AppController
import com.example.shoppingcart.model.product.ProductDao
import com.example.shoppingcart.model.product.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class DataBase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: DataBase? = null

        fun getInstance(): DataBase? {
            if (INSTANCE == null) {
                synchronized(DataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        AppController.getInstance().applicationContext,
                        DataBase::class.java, "groceries_stotre.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}