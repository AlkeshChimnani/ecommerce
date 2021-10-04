package com.alkesh.ecommerce.presentation.screens.screen2.activity

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alkesh.ecommerce.R
import com.alkesh.ecommerce.common.base.activity.AppBaseActivity
import com.alkesh.ecommerce.dataProvider.network.models.dto.ImageDTO
import com.alkesh.ecommerce.databinding.ActivityScreen2Binding
import com.alkesh.ecommerce.presentation.general.widget.imageWidget.adapter.ImageWidgetAdapter
import com.alkesh.ecommerce.presentation.screens.screen2.viewModel.Screen2ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_dashboard.swipeRefresh
import kotlinx.android.synthetic.main.activity_screen2.*
import kotlinx.android.synthetic.main.layout_toolbar.*

@AndroidEntryPoint
class Screen2Activity : AppBaseActivity<ActivityScreen2Binding>() {
    private val viewModel: Screen2ViewModel by viewModels()
    override fun init() {
        toolbar.setup(this, getString(R.string.activity_screen2_title), true)

    }

    override fun setEvents() {
        swipeRefresh.setOnRefreshListener {
            viewModel.getData()
        }
    }

    override fun setObservers() {
        viewModel.listImages.observe(this, Observer {
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
        return R.layout.activity_screen2
    }


    override fun dataBinding(dataBinder: ActivityScreen2Binding) {

    }

    private fun populateView(list: ArrayList<ImageDTO>) {
        val adapter = ImageWidgetAdapter(this, list)
        gridview.adapter = adapter
    }
}