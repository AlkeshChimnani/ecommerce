package com.alkesh.ecommerce.presentation.general.widget.imageWidget.view

import android.content.Context
import android.util.AttributeSet
import com.alkesh.ecommerce.R
import com.alkesh.ecommerce.common.base.component.BaseComponentBaseRelativeLayout
import com.alkesh.ecommerce.dataProvider.network.models.dto.ImageDTO
import com.alkesh.ecommerce.presentation.general.widget.imageWidget.adapter.ImageWidgetAdapter
import com.alkesh.ecommerce.presentation.general.widget.imageWidget.listener.OnWidgetClicked
import kotlinx.android.synthetic.main.widget_image.view.*

class ImageWidget : BaseComponentBaseRelativeLayout {
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
        inflate(context, R.layout.widget_image, this)

    }

    private fun initAttributes(attrs: AttributeSet?) {
        val styledAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.ImageWidget)
        styledAttributes.recycle()
    }

    //Set before you populate
    fun setOnWidgetClickedListener(listener: OnWidgetClicked) {
        onWidgetClicked = listener
    }

    fun populateWidget(list: ArrayList<ImageDTO>) {
        gridview.numColumns = list.size
        val adapter = ImageWidgetAdapter(this.context, list, onWidgetClicked)
        gridview.adapter = adapter
    }

}