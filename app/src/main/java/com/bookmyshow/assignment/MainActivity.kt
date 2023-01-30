package com.bookmyshow.assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bookmyshow.assignment.databinding.ActivityMainBinding
import com.bookmyshow.assignment.di.DaggerAppComponentProvider
import com.bookmyshow.core.ImageLoader
import com.bookmyshow.venues.FeatureOneActivity
import com.bookmyshow.venues.viewmodel.VenuesViewModel
import com.bookmyshow.venues.viewmodel.SharedViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    private var imageCta: ImageView? = null

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: VenuesViewModel by viewModels<VenuesViewModel> { factory }
    private val sharedViewModel: SharedViewModel by viewModels<SharedViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DaggerAppComponentProvider.getAppComponent().inject(mainActivity = this)
        imageCta = findViewById(R.id.image_cta)
        imageLoader.loadImage(
            imageUrl = "https://static.businessworld.in/article/article_extra_large_image/1609147522_O1aw88_BMS.jpg",
            imageView = requireNotNull(binding.imageCta)
        )

        imageCta?.setOnClickListener {
            startActivity(
                Intent(this, FeatureOneActivity::class.java)
            )
        }

        binding.apply {
            filter.setOnClickListener {
                sharedViewModel.onFilterClick()
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}