package com.example.showtimefilter.di

import com.bookmyshow.core.di.CoreComponentProvider


object ShowTimeDaggerProvider {

    val component: ShowTimeComponent by lazy {
        DaggerShowTimeComponent.factory().create(
            coreComponent = CoreComponentProvider.coreComponent
        )
    }
}