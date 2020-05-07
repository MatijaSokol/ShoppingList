package hr.ferit.matijasokol.shoppinglist.app

import android.app.Application
import hr.ferit.matijasokol.shoppinglist.db.ShoppingDatabase
import hr.ferit.matijasokol.shoppinglist.repository.ShoppingRepository
import hr.ferit.matijasokol.shoppinglist.ui.shoppingList.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingApplication : Application(), KodeinAware {

    override val kodein: Kodein
        get() = Kodein.lazy {
            import(androidXModule(this@ShoppingApplication))
            bind() from singleton { ShoppingDatabase.invoke(instance()) }
            bind() from singleton {
                ShoppingRepository(
                    instance()
                )
            }
            bind() from provider {
                ShoppingViewModelFactory(instance())
            }
        }
}