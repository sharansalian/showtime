package com.bookmyshow.assignment.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bookmyshow.common_ui.di.ViewModelFactory
import com.bookmyshow.common_ui.di.ViewModelKey
import com.bookmyshow.feature_one.viewmodel.FeatureOneViewModel
import com.bookmyshow.feature_one.viewmodel.SharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    fun bindSharedViewModel(sharedViewModel: SharedViewModel): ViewModel

}