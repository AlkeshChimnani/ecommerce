package com.alkesh.ecommerce.common.util.glideImageLoader

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
    }


    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        /*AppComponentsConstants.okHttpClient?.let {
            registry.replace(
                GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(it)
            )
        }*/

    }
}