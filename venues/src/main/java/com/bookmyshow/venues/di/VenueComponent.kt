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
    modules = [VenueModule::class]
)
@Fragment
interface VenueComponent {

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent
        ): VenueComponent
    }

    fun inject(activity: FeatureOneActivity)

    fun inject(venuesFragment: VenuesFragment)
}