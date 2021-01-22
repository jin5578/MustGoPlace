package com.example.mustgoplace.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mustgoplace.model.Event
import com.example.mustgoplace.ui.base.DisposableViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SplashViewModel @Inject constructor() : DisposableViewModel() {

    private val _navigateToHome = MutableLiveData<Event<Unit>>()
    val navigateToHome: LiveData<Event<Unit>>
        get() = _navigateToHome

    init {
        delayTime()
    }

    private fun delayTime() {
        compositeDisposable add
                Completable.timer(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        _navigateToHome.value = Event(Unit)
                    }
    }
}