package com.alkesh.ecommerce.presentation.screens.screen2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.alkesh.ecommerce.R
import com.alkesh.ecommerce.common.util.glideImageLoader.ImageUtil
import com.alkesh.ecommerce.dataProvider.network.models.dto.ImageDTO
import kotlinx.android.synthetic.main.cell_image_grid.view.*

class GridImageAdapter(
    context: Context, private val list: List<ImageDTO>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Inflate the custom view
        val inflater =
            parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.cell_image_grid, null)
        val model = list[position]
        model.let {
            view.ivCategory.layoutParams.width = model.width
            view.ivCategory.layoutParams.height = model.height
            view.ivCategory.requestLayout();
            ImageUtil.loadImage(view.ivCategory.context, view.ivCategory, it.url)
        }

        return view
    }

    override fun getItem(position: Int): Any? {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}