package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun SectionHeader2(
    text: TextSpec,
    icon: IconSpec? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = CTTheme.spacing.large)
            .padding(top = CTTheme.spacing.large),
        horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            CTIcon(
                icon = icon,
                size = Dimens.IconSize.Medium,
                color = CTTheme.color.textDefault,
            )
        }

        CTTextView(
            text = text,
            style = CTTheme.typography.header2,
            color = CTTheme.color.textDefault,
        )
    }
}

object SectionHeader2 {
    fun lazyItem(
        scope: LazyListScope,
        text: TextSpec,
        icon: ComposeProvider<IconSpec>? = null,
        key: Any? = text.hashCode(),
    ) {
        scope.item(key = key, contentType = contentType) {
            SectionHeader2(text = text, icon = icon?.invoke())
        }
    }

    private const val contentType = "SectionHeader"
}
