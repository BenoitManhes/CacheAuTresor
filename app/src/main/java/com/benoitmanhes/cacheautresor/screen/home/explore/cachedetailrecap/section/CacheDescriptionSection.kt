package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.Section
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun CacheDescriptionSection(
    description: TextSpec,
    modifier: Modifier = Modifier,
) {
    Section(title = TextSpec.Resources(R.string.cacheDetail_descriptionSection_title)) {
        CTTextView(
            text = description,
            modifier = modifier.padding(horizontal = CTTheme.spacing.large),
            style = CTTheme.typography.body,
            maxLine = Int.MAX_VALUE,
        )
    }
}

object CacheDescriptionSection {
    private const val contentType: String = "CacheDescriptionSection"

    fun item(
        scope: LazyListScope,
        description: TextSpec,
        key: Any? = description.hashCode(),
        modifier: Modifier = Modifier,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            CacheDescriptionSection(
                description = description,
                modifier = modifier,
            )
        }
    }
}
