package com.bookmyshow.venues.di

import com.bookmyshow.core.di.CoreComponentProvider


object VenueDaggerProvider {

    val component: VenueComponent by lazy {
        DaggerVenueComponent.factory().create(
            coreComponent = CoreComponentProvider.coreComponent
        )
    }
}