package com.example.cryptocoinstracker.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.cryptocoinstracker.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider

//@ApplicationScope
//class RefreshWorkerFactory @Inject constructor(
//    private val workerProviders: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>,
//) : WorkerFactory() {
//    override fun createWorker(
//        appContext: Context,
//        workerClassName: String,
//        workerParameters: WorkerParameters,
//    ): ListenableWorker? {
//        return when (workerClassName) {
//            RefreshDataWorker::class.qualifiedName -> {
//                workerProviders[RefreshDataWorker::class.java].get()
//            }
//            else -> null
//        }
//    }
//}