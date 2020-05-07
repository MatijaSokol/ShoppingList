package hr.ferit.matijasokol.shoppinglist.db

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.ferit.matijasokol.shoppinglist.model.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM ShoppingItems")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}