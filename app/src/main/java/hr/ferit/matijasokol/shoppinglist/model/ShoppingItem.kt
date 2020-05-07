package hr.ferit.matijasokol.shoppinglist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingItems")
data class ShoppingItem(
    var name: String,
    var amount: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    //needed for diffUtil comparing
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass){
            return false
        }

        other as ShoppingItem

        if (name != other.name || amount != other.amount){
            return false
        }

        return true
    }
}