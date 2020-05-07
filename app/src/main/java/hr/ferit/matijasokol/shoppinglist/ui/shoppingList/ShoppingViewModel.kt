package hr.ferit.matijasokol.shoppinglist.ui.shoppingList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.matijasokol.shoppinglist.model.ShoppingItem
import hr.ferit.matijasokol.shoppinglist.repository.ShoppingRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun upsert(item: ShoppingItem) = viewModelScope.launch(IO) {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = viewModelScope.launch(IO) {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}