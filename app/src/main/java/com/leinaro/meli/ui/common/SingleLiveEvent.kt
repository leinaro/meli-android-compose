package com.leinaro.meli.ui.common

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MediatorLiveData<T>() {
    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<in T>
    ) {
        super.observe(owner, Observer<T>{t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }
}