package com.bookmyshow.feature_one.di

import com.bookmyshow.core.di.CoreComponent
import com.bookmyshow.feature_one.FeatureOneActivity
import dagger.Component

@Component(
    dependencies = [
        CoreComponent::class
    ],
    modules = [FeatureOneModule::class]
)
interface FeatureOneComponent {

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent
        ): FeatureOneComponent
    }

    fun inject(activity: FeatureOneActivity)
}