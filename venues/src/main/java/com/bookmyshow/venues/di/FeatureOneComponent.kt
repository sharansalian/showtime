package com.bookmyshow.venues.di

import com.bookmyshow.common_ui.scopes.Fragment
import com.bookmyshow.core.di.CoreComponent
import com.bookmyshow.venues.FeatureOneActivity
import com.bookmyshow.venues.ui.VenuesFragment
import dagger.Component

@Component(
    dependencies = [
        CoreComponent::class
    ],
    modules = [FeatureOneModule::class]
)
@Fragment
interface FeatureOneComponent {

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent
        ): FeatureOneComponent
    }

    fun inject(activity: FeatureOneActivity)

    fun inject(venuesFragment: VenuesFragment)
}