package hr.ferit.matijasokol.shoppinglist.common

import android.content.Context
import android.widget.Toast

fun Context.displayToast(message: String, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, length).show()