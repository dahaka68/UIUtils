package com.p97.uimodule

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.databinding.BindingAdapter
import com.google.android.material.color.MaterialColors

object NavBarBindingAdapter {
    private const val EDGE_TO_EDGE_BAR_ALPHA = 128

    @BindingAdapter("lightNavigationBar")
    @JvmStatic
    fun lightNavigationBar(view: View, isLightNavBar: Boolean) {
        val window = (view.context as Activity).window
        if (isLightNavBar == isLightCurrentNavColor(
                window
            )
        ) return

        window.navigationBarColor =
            getNavBarColor(
                isLightNavBar,
                view.context
            )
        val currentNavBar = if (isLightNavBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR else 0
        window.decorView.systemUiVisibility = currentNavBar
    }

    @SuppressLint("RestrictedApi")
    private fun getNavBarColor(isLightNavBar: Boolean, context: Context): Int {
        if (isLightNavBar && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            val opaqueNavBarColor = MaterialColors.getColor(context, android.R.attr.navigationBarColor, Color.BLACK)
            return ColorUtils.setAlphaComponent(opaqueNavBarColor,
                EDGE_TO_EDGE_BAR_ALPHA
            )
        }
        return if (isLightNavBar) {
            Color.TRANSPARENT
        } else {
            MaterialColors.getColor(context, 0, ContextCompat.getColor(context,
                R.color.navBarColor
            ))
        }
    }

    private fun isLightCurrentNavColor(window: Window): Boolean {
        return window.navigationBarColor == Color.TRANSPARENT
    }
}