package com.bookmyshow.feature_one.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bookmyshow.common_ui.di.ViewModelFactory
import com.bookmyshow.common_ui.di.ViewModelKey
import com.bookmyshow.common_ui.scopes.Fragment
import com.bookmyshow.feature_one.viewmodel.FeatureOneViewModel
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

}
