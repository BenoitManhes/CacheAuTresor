package com.benoitmanhes.designsystem.molecule.divider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.designsystem.atoms.CTDivider
import com.benoitmanhes.designsystem.atoms.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.colorScheme
import com.benoitmanhes.designsystem.theme.spacing
import com.benoitmanhes.designsystem.theme.typo
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun CTDividerText(
    text: TextSpec,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
    ) {
        CTDivider(modifier = Modifier.weight(1f))
        CTTextView(
            text = text,
            style = MaterialTheme.typo.caption,
            color = MaterialTheme.colorScheme.placeholder,
        )
        CTDivider(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun PreviewSeparatorWithText() {
    CTTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.large),
            contentAlignment = Alignment.Center,
        ) {
            CTDividerText(text = TextSpec.RawString("ou"))
        }
    }
}
