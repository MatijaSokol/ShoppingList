package hr.ferit.matijasokol.shoppinglist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.matijasokol.shoppinglist.R
import hr.ferit.matijasokol.shoppinglist.model.ShoppingItem
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    private val onPlusMinusClicked: (ShoppingItem) -> Unit,
    private val onDeleteClicked: (ShoppingItem) -> Unit
) : ListAdapter<ShoppingItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShoppingItemViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShoppingItem>() {

            override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ShoppingItemViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bind(item: ShoppingItem) {
            with(containerView) {
                tvName.text = item.name
                tvAmount.text = "${item.amount}"

                ivDelete.setOnClickListener { onDeleteClicked(item) }

                ivPlus.setOnClickListener {
                    item.amount++
                    onPlusMinusClicked(item)
                }

                ivMinus.setOnClickListener {
                    if (item.amount > 0) {
                        item.amount--
                        onPlusMinusClicked(item)
                    }
                }
            }
        }
    }
}