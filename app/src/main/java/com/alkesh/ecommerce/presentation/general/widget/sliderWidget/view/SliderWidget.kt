package com.alkesh.ecommerce.presentation.general.widget.sliderWidget.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.alkesh.ecommerce.R
import com.alkesh.ecommerce.common.base.component.BaseComponentBaseRelativeLayout
import com.alkesh.ecommerce.common.util.glideImageLoader.ImageUtil
import com.alkesh.ecommerce.dataProvider.network.models.dto.ImageDTO
import com.alkesh.ecommerce.presentation.general.widget.imageWidget.listener.OnWidgetClicked
import kotlinx.android.synthetic.main.widget_slider.view.*

class SliderWidget : BaseComponentBaseRelativeLayout {
    private var onWidgetClicked: OnWidgetClicked? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(
        context: Context,
        attributeSet: AttributeSet?,
        defaultStyleAttributeId: Int
    ) : super(context, attributeSet, defaultStyleAttributeId) {
        init()
        initAttributes(attributeSet)
    }

    private fun init() {
        inflate(context, R.layout.widget_slider, this)

    }

    private fun initAttributes(attrs: AttributeSet?) {
        val styledAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.SliderWidget)
        styledAttributes.recycle()
    }

    //Set before you populate
    fun setOnWidgetClickedListener(listener: OnWidgetClicked) {
        onWidgetClicked = listener
    }

    fun populateWidget(list: ArrayList<ImageDTO>) {
        carouselView.pageCount = list.size
        carouselView.setImageListener { position, imageView ->
            list.let {
                val model = it[position]
                val banner = model.url
                imageView.scaleType = ImageView.ScaleType.FIT_XY
                ImageUtil.loadImage(imageView.context, imageView, banner)
                imageView.setOnClickListener(OnClickListener {
                    onWidgetClicked?.let {
                        it.onViewClicked()
                    }
                })
            }
        }
    }
}