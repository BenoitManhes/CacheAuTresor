package com.benoitmanhes.designsystem.atoms

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun CTIcon(
    icon: IconSpec,
    size: Dimens.IconSize,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    onClick: (() -> Unit)? = null,
) {
    if (onClick != null) {
        IconButton(onClick = onClick) {
            Icon(
                painter = icon.painter(),
                contentDescription = icon.contentDescription,
                modifier = modifier.size(size.dp),
                tint = color,
            )
        }
    } else {
        Icon(
            painter = icon.painter(),
            contentDescription = icon.contentDescription,
            modifier = modifier.size(size.dp),
            tint = color,
        )
    }
}
