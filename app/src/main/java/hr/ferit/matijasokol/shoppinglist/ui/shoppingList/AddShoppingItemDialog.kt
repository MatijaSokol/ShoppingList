package hr.ferit.matijasokol.shoppinglist.ui.shoppingList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import hr.ferit.matijasokol.shoppinglist.R
import hr.ferit.matijasokol.shoppinglist.model.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog : DialogFragment() {

    interface DialogListener {
        fun onAddButtonClicked(item: ShoppingItem)
        fun onDataMissing()
    }

    private var dialogListener: DialogListener? = null

    companion object {
        fun newInstance() = AddShoppingItemDialog()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialogListener = context as DialogListener
    }

    override fun onDetach() {
        super.onDetach()
        dialogListener = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_add_shopping_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvAdd.setOnClickListener{
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                dialogListener?.onDataMissing()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            dialogListener?.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener { dismiss() }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }
}