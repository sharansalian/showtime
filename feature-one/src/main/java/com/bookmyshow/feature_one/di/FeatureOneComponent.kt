package com.bookmyshow.feature_one.di

import com.bookmyshow.common_ui.scopes.Fragment
import com.bookmyshow.core.di.CoreComponent
import com.bookmyshow.feature_one.FeatureOneActivity
import com.bookmyshow.feature_one.ui.VenuesFragment
import com.example.domain.repository.VenueRepository
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