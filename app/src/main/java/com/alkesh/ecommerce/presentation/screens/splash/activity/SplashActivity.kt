package com.alkesh.ecommerce.presentation.screens.splash.activity

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alkesh.ecommerce.R
import com.alkesh.ecommerce.common.base.activity.AppBaseActivity
import com.alkesh.ecommerce.databinding.ActivitySplashBinding
import com.alkesh.ecommerce.presentation.screens.dashboard.activity.DashboardActivity
import com.alkesh.ecommerce.presentation.screens.splash.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppBaseActivity<ActivitySplashBinding>() {
    private val viewModel: SplashViewModel by viewModels()
    override fun init() {

    }

    override fun setEvents() {
    }

    override fun setObservers() {
        viewModel.navigateListOfficeLocations.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
        })
    }

    override fun getLayoutResId() = R.layout.activity_splash
    override fun dataBinding(dataBinder: ActivitySplashBinding) {

    }
}