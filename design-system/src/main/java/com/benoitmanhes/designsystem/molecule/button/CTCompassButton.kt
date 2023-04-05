package com.benoitmanhes.designsystem.molecule.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.benoitmanhes.designsystem.R
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun CTCompassButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Image(
        modifier = modifier
            .size(Dimens.Size.compassButtonSize)
            .clip(CTTheme.shape.circle)
            .clickable { onClick() },
        painter = painterResource(id = R.drawable.compass_button),
        contentDescription = null,
    )
}