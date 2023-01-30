package com.bookmyshow.venues

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bookmyshow.venues.di.FeatureOneDaggerProvider
import com.bookmyshow.venues.viewmodel.FeatureOneViewModel
import javax.inject.Inject

class FeatureOneActivity : AppCompatActivity() {


    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: FeatureOneViewModel by viewModels<FeatureOneViewModel>() { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FeatureOneDaggerProvider.component.inject(this)
        setContentView(R.layout.activity_feature_one)
    }
}