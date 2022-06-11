package com.muzafferatmaca.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Muzaffer Atmaca on 12.06.2022.
 */
open class BaseViewModel(application: Application) :AndroidViewModel(application),CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}