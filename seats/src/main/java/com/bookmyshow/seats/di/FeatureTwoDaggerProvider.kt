package com.bookmyshow.seats.di

import com.bookmyshow.core.di.CoreComponentProvider

object FeatureTwoDaggerProvider {

    val component: FeatureTwoComponent by lazy {
        DaggerFeatureTwoComponent.factory().create(
            coreComponent = CoreComponentProvider.coreComponent
        )
    }
}