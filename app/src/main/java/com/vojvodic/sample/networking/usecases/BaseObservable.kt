package com.vojvodic.sample.networking.usecases

import java.util.*
import java.util.concurrent.ConcurrentHashMap

abstract class BaseObservable<LISTENER> {

    //Thread safe set of listeners
    val mListeners = Collections.newSetFromMap(ConcurrentHashMap<LISTENER, Boolean>(1))

    fun registerListener(listener: LISTENER) {
        mListeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER) {
        mListeners.remove(listener)
    }

    protected fun getListeners(): Set<LISTENER> {
        return Collections.unmodifiableSet(mListeners)
    }
}