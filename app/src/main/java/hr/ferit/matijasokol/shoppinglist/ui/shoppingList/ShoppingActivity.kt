package hr.ferit.matijasokol.shoppinglist.ui.shoppingList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.matijasokol.shoppinglist.R
import hr.ferit.matijasokol.shoppinglist.common.displayToast
import hr.ferit.matijasokol.shoppinglist.model.ShoppingItem
import hr.ferit.matijasokol.shoppinglist.ui.adapters.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware, AddShoppingItemDialog.DialogListener {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()
    private val viewModel by lazy { ViewModelProvider(this, factory).get(ShoppingViewModel::class.java) }
    private val shoppingItemAdapter by lazy { ShoppingItemAdapter({ onPlusMinusClicked(it) }, { onDeleteClicked(it) }) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        setRecycler()
        setListeners()
        setViewModel()
    }

    private fun setRecycler() {
        rvShoppingItems.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ShoppingActivity)
            adapter = shoppingItemAdapter
            addItemDecoration(DividerItemDecoration(this@ShoppingActivity, RecyclerView.VERTICAL))
        }
    }

    private fun setListeners() {
        fab.setOnClickListener {
            AddShoppingItemDialog.newInstance().show(supportFragmentManager, "")
        }
    }

    private fun setViewModel() {
        viewModel.getAllShoppingItems().observe(this, Observer<List<ShoppingItem>> {
            (rvShoppingItems.adapter as ShoppingItemAdapter).apply {
                submitList(it)
            }
        })
    }

    override fun onAddButtonClicked(item: ShoppingItem) {
        viewModel.upsert(item)
    }

    override fun onDataMissing() {
        displayToast(getString(R.string.enter_all_information))
    }

    private fun onPlusMinusClicked(shoppingItem: ShoppingItem) {
        viewModel.upsert(shoppingItem)
    }

    private fun onDeleteClicked(shoppingItem: ShoppingItem) {
        viewModel.delete(shoppingItem)
    }
}
