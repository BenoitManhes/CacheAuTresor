package com.benoitmanhes.cacheautresor.common.maps

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.benoitmanhes.cacheautresor.R

/**
 * [DrawableRes] is use instead of Composable, because google map marker does not support composable icon for now
 */
sealed class CacheMarkerIcon(
    @DrawableRes private val iconRes: Int,
    @DrawableRes private val iconSelectedRes: Int,
    var tint: Color,
    var iconText: String? = null,
) {
    fun getDrawable(
        isSelected: Boolean,
        context: Context,
    ): Drawable? {
        val drawableRes = if (isSelected) iconSelectedRes else iconRes
        // retrieve the actual drawable
        return ContextCompat.getDrawable(context, drawableRes)
    }

    class Classical(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_classical,
        iconSelectedRes = R.drawable.marker_selected_classical,
        tint = color,
    )

    class Piste(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_piste,
        iconSelectedRes = R.drawable.marker_selected_piste,
        tint = color,
    )

    class Mystery(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_mystery,
        iconSelectedRes = R.drawable.marker_selected_mystery,
        tint = color,
    )

    class Coop(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_coop,
        iconSelectedRes = R.drawable.marker_selected_coop,
        tint = color,
    )

    class ClassicalFocus(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_classical_focus,
        iconSelectedRes = R.drawable.marker_classical_focus,
        tint = color,
    )

    class PisteFocus(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_piste_focus,
        iconSelectedRes = R.drawable.marker_piste_focus,
        tint = color,
    )

    class MysteryFocus(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_mystery_focus,
        iconSelectedRes = R.drawable.marker_mystery_focus,
        tint = color,
    )

    class CoopFocus(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_coop_focus,
        iconSelectedRes = R.drawable.marker_coop_focus,
        tint = color,
    )

    class Found(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_found,
        iconSelectedRes = R.drawable.marker_selected_found,
        tint = color,
    )

    class Owner(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_owner,
        iconSelectedRes = R.drawable.marker_selected_owned,
        tint = color,
    )

    class Done(
        color: Color,
    ) : CacheMarkerIcon(
        iconRes = R.drawable.marker_done,
        iconSelectedRes = R.drawable.marker_done,
        tint = color,
    )

    class Locked(color: Color) : CacheMarkerIcon(
        iconRes = R.drawable.marker_lock,
        iconSelectedRes = R.drawable.marker_selected_lock,
        tint = color,
    )

    class Empty(
        iconText: String?,
        color: Color,
    ) : CacheMarkerIcon(
        iconRes = R.drawable.marker_empty,
        iconSelectedRes = R.drawable.marker_current_empty,
        iconText = iconText,
        tint = color,
    )

    class Current(
        iconText: String?,
        color: Color,
    ) : CacheMarkerIcon(
        iconRes = R.drawable.marker_current_empty,
        iconSelectedRes = R.drawable.marker_current_empty,
        iconText = iconText,
        tint = color,
    )
}
