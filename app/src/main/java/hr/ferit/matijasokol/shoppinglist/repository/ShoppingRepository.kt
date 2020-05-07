package hr.ferit.matijasokol.shoppinglist.repository

import hr.ferit.matijasokol.shoppinglist.db.ShoppingDatabase
import hr.ferit.matijasokol.shoppinglist.model.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}