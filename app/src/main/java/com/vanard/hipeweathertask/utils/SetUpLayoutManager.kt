package com.vanard.hipeweathertask.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object SetUpLayoutManager {
    fun horizontalLinearLayout(context: Context, targetRecyclerView: RecyclerView) {
        val placeLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        targetRecyclerView.setHasFixedSize(true)
        targetRecyclerView.layoutManager = placeLayoutManager
    }
}