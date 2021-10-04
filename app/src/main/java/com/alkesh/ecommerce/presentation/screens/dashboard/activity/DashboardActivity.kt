package com.alkesh.ecommerce.presentation.screens.dashboard.activity

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alkesh.ecommerce.R
import com.alkesh.ecommerce.common.base.activity.AppBaseActivity
import com.alkesh.ecommerce.common.repository.WidgetType
import com.alkesh.ecommerce.dataProvider.network.models.dto.CategoryDTO
import com.alkesh.ecommerce.databinding.ActivityDashboardBinding
import com.alkesh.ecommerce.presentation.general.widget.imageWidget.listener.OnWidgetClicked
import com.alkesh.ecommerce.presentation.general.widget.imageWidget.view.ImageWidget
import com.alkesh.ecommerce.presentation.general.widget.sliderWidget.view.SliderWidget
import com.alkesh.ecommerce.presentation.screens.dashboard.viewModel.DashboardViewModel
import com.alkesh.ecommerce.presentation.screens.screen2.activity.Screen2Activity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.layout_toolbar.*

@AndroidEntryPoint
class DashboardActivity : AppBaseActivity<ActivityDashboardBinding>(), OnWidgetClicked {
    private val viewModel: DashboardViewModel by viewModels()
    override fun init() {
        toolbar.setup(this, getString(R.string.activity_dashboard_title), false)

    }

    override fun setEvents() {
        swipeRefresh.setOnRefreshListener {
            viewModel.getData()
        }
    }

    override fun setObservers() {
        viewModel.listCategories.observe(this, Observer {
            it?.let {
                populateView(it)
            }
        })
        viewModel.isLoading.observe(this, Observer {
            it?.let {
                if (it) {
                    showLoadingDialog()
                    swipeRefresh.isRefreshing = true
                } else {
                    hideLoadingDialog()
                    swipeRefresh.isRefreshing = false
                }
            }
        })
        viewModel.showMessage.observe(this, Observer {
            it?.let {
                showMessage(it)
            }
        })
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_dashboard
    }


    override fun dataBinding(dataBinder: ActivityDashboardBinding) {

    }

    private fun populateView(listCategories: ArrayList<CategoryDTO>) {
        linearlayout.removeAllViews()
        for (category in listCategories) {
            if (category.type == WidgetType.IMAGE.value) {
                val iv = ImageWidget(this)
                iv.setOnWidgetClickedListener(this)
                iv.populateWidget(category.listImages)
                linearlayout.addView(iv)
            } else if (category.type == WidgetType.SLIDER.value) {
                val iv = SliderWidget(this)
                iv.setOnWidgetClickedListener(this)
                iv.populateWidget(category.listImages)
                linearlayout.addView(iv)
            }

        }
    }

    override fun onViewClicked() {
        startAnotherActivity(Intent(this, Screen2Activity::class.java))
    }
}