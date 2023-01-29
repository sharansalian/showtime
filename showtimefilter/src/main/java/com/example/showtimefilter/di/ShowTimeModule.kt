package com.example.showtimefilter.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bookmyshow.common_ui.di.ViewModelFactory
import com.bookmyshow.common_ui.di.ViewModelKey
import com.bookmyshow.common_ui.scopes.Fragment
import com.example.showtimefilter.ui.ShowTimeFilterBottomSheetViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ShowTimeModule {

    @Binds
    @Fragment
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ShowTimeFilterBottomSheetViewModel::class)
    fun bindFeatureOneViewModel(showTimeFilterBottomSheetViewModel: ShowTimeFilterBottomSheetViewModel): ViewModel

}
