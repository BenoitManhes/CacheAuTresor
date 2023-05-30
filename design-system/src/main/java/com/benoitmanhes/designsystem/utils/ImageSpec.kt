package com.benoitmanhes.designsystem.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter

sealed interface ImageSpec {

    val contentDescription: String?

    @Composable
    fun painter(): Painter

    data class ResImage(
        @DrawableRes
        private val drawableRes: Int,
        override val contentDescription: String?,
    ) : ImageSpec {

        @Composable
        override fun painter(): Painter = painterResource(id = drawableRes)
    }

    data class BitmapImage(
        val imageBitmap: ImageBitmap,
        override val contentDescription: String?,
        private val filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
    ) : ImageSpec {
        @Composable
        override fun painter(): Painter {
            return remember(imageBitmap) { BitmapPainter(imageBitmap, filterQuality = filterQuality) }
        }
    }

    data class UrlImage(
        private val url: String,
        override val contentDescription: String?,
    ) : ImageSpec {
        @Composable
        override fun painter(): Painter = rememberAsyncImagePainter(
            model = url
        )
    }
}
