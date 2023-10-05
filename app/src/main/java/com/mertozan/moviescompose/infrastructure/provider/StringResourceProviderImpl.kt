package com.mertozan.moviescompose.infrastructure.provider

import android.content.Context

class StringResourceProviderImpl(
    private val context: Context
) : StringResourceProvider {

    override fun getString(id: Int) = context.getString(id)

}