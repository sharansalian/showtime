package com.bookmyshow.venues.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bookmyshow.common_ui.di.ViewModelFactory
import com.bookmyshow.common_ui.di.ViewModelKey
import com.bookmyshow.common_ui.scopes.Fragment
import com.bookmyshow.venues.repository.VenueRepositoryImpl
import com.bookmyshow.venues.viewmodel.FeatureOneViewModel
import com.bookmyshow.venues.viewmodel.SharedViewModel
import com.example.domain.repository.VenueRepository
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FeatureOneModule {

    @Binds
    @Fragment
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FeatureOneViewModel::class)
    fun bindFeatureOneViewModel(featureOneViewModel: FeatureOneViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    fun bindSharedViewModel(sharedViewModel: SharedViewModel): ViewModel

    @Binds
    fun bindVenueRepository(venueRepositoryImpl: VenueRepositoryImpl): VenueRepository

}
