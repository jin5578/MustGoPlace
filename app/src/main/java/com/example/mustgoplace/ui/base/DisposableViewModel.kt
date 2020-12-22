package com.example.mustgoplace.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class DisposableViewModel : ViewModel() {

    infix fun CompositeDisposable.add(d: Disposable) = this.add(d)

    val compositeDisposable = CompositeDisposable()

    private val TAG = "DisposableViewModel"

    override fun onCleared() {
        compositeDisposable.clear()

        Log.e(TAG, "compositeDisposable Clear")

        super.onCleared()
    }
}