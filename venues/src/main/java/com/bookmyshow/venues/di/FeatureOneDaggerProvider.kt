package com.bookmyshow.venues.di

import com.bookmyshow.core.di.CoreComponentProvider


object FeatureOneDaggerProvider {

    val component: FeatureOneComponent by lazy {
        DaggerFeatureOneComponent.factory().create(
            coreComponent = CoreComponentProvider.coreComponent
        )
    }
}