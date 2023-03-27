package com.benoitmanhes.cacheautresor.screen.home.explore

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.benoitmanhes.cacheautresor.R
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/**
 * [DrawableRes] is use instead of Composable, because google map marker does not support composable icon for now
 */
sealed class CacheMarker(
    @DrawableRes private val iconRes: Int,
    @DrawableRes private val iconSelectedRes: Int,
) {

    fun bitmapDescriptor(
        isSelected: Boolean,
        context: Context,
    ): BitmapDescriptor? {
        val drawableRes = if (isSelected) iconSelectedRes else iconRes
        // retrieve the actual drawable
        val drawable = ContextCompat.getDrawable(context, drawableRes) ?: return null
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        val bm = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // draw it onto the bitmap
        val canvas = android.graphics.Canvas(bm)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bm)
    }

    object Classical : CacheMarker(
        iconRes = R.drawable.marker_classical,
        iconSelectedRes = R.drawable.marker_selected_classical,
    )

    object Piste : CacheMarker(
        iconRes = R.drawable.marker_piste,
        iconSelectedRes = R.drawable.marker_selected_piste,
    )

    object Mystery : CacheMarker(
        iconRes = R.drawable.marker_mystery,
        iconSelectedRes = R.drawable.marker_selected_mystery,
    )

    object Coop : CacheMarker(
        iconRes = R.drawable.marker_coop,
        iconSelectedRes = R.drawable.marker_selected_coop,
    )

    object Found : CacheMarker(
        iconRes = R.drawable.marker_found,
        iconSelectedRes = R.drawable.marker_selected_found,
    )

    object Owner : CacheMarker(
        iconRes = R.drawable.marker_owner,
        iconSelectedRes = R.drawable.marker_selected_owned,
    )
}
