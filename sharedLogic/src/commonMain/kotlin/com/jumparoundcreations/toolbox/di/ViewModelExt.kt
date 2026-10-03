package com.jumparoundcreations.toolbox.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass

/**
 * Manually triggers the disposal of this [ViewModel].
 *
 * This is a workaround to call the internal `clear()` method on the ViewModel class,
 * which is necessary when managing ViewModels manually in environments like SwiftUI.
 */
fun ViewModel.dispose() {
    val store = ViewModelStore()
    val provider =
        ViewModelProvider.create(
            store = store,
            factory =
                object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(
                        modelClass: KClass<T>,
                        extras: CreationExtras
                    ): T = this@dispose as T
                }
        )

    // Using the actual class of this instance to ensure the provider can "find" it in the factory.
    provider.get(this::class)

    // Clearing the store triggers the internal clear() method on all ViewModels it contains.
    store.clear()
}
