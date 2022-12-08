package com.benoitmanhes.designsystem.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource

sealed interface IconSpec {

    val contentDescription: String?

    @Composable
    fun painter(): Painter

    data class ResIcon(
        @DrawableRes
        private val drawableRes: Int,
        override val contentDescription: String?,
    ) : IconSpec {

        @Composable
        override fun painter(): Painter = painterResource(id = drawableRes)
    }

    data class VectorIcon(
        private val imageVector: ImageVector,
        override val contentDescription: String?,
    ) : IconSpec {

        @Composable
        override fun painter(): Painter = rememberVectorPainter(imageVector)
    }

    data class BitmapIcon(
        val imageBitmap: ImageBitmap,
        override val contentDescription: String?,
        private val filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
    ) : IconSpec {
        @Composable
        override fun painter(): Painter {
            return remember(imageBitmap) { BitmapPainter(imageBitmap, filterQuality = filterQuality) }
        }
    }
}
