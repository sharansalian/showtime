package com.example.showtimefilter.di

import com.bookmyshow.common_ui.scopes.Fragment
import com.bookmyshow.core.di.CoreComponent
import com.example.showtimefilter.ui.ShowTimeFilterBottomSheetFragment
import dagger.Component

@Component(
    dependencies = [
        CoreComponent::class
    ],
    modules = [ShowTimeModule::class]
)
@Fragment
interface ShowTimeComponent {

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent
        ): ShowTimeComponent
    }

    fun inject(showTimeFilterBottomSheetFragment: ShowTimeFilterBottomSheetFragment)
}