package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.row.MapRowPickerState
import com.benoitmanhes.cacheautresor.common.maps.CacheMarkerIcon
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTDivider
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.button.iconbutton.CTIconButton
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.ctClickable
import com.benoitmanhes.domain.model.Coordinates

@Composable
fun CrewStepsCard(
    crewName: TextSpec,
    steps: List<MapRowPickerState>,
    onClickAddStep: () -> Unit,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = CTTheme.shape.medium,
        border = BorderStroke(CTTheme.stroke.medium, brush = CTTheme.gradient.surfacePrimary),
        color = CTTheme.color.surface,
    ) {
        Column {
            Header(
                title = crewName,
                onClickEdit = onClickEdit,
                onClickDelete = onClickDelete
            )
            ContentCard(steps = steps)
            CTDivider(
                modifier = Modifier.fillMaxWidth(),
                color = CTTheme.color.strokeDisable,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .ctClickable(onClickAddStep)
                    .padding(horizontal = CTTheme.spacing.medium, vertical = CTTheme.spacing.small),
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
                ) {
                    CTIcon(icon = CTTheme.icon.Add, size = Dimens.IconSize.Medium)
                    CTTextView(
                        text = TextSpec.Resources(R.string.cacheEditor_addStepButton_title),
                        style = CTTheme.typography.body,
                        color = CTTheme.color.textOnSurface,
                    )
                }
            }
        }
    }
}

@Composable
private fun Header(
    title: TextSpec,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CTTheme.gradient.surfacePrimary)
            .padding(end = CTTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier
                .ctClickable(
                    onClick = onClickEdit,
                    rippleColor = CTTheme.color.rippleOnPrimary,
                )
                .padding(vertical = CTTheme.spacing.small)
                .padding(horizontal = CTTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.medium),
        ) {
            CTTextView(
                text = title,
                style = CTTheme.typography.body,
                color = CTTheme.color.textOnSurfacePrimary,
            )
            CTIcon(
                icon = CTTheme.icon.Edit,
                size = Dimens.IconSize.Medium,
                color = CTTheme.color.textOnSurfacePrimary,
            )
        }

        CTIconButton(
            icon = CTTheme.icon.Delete,
            size = Dimens.IconButtonSize.Medium,
            iconColor = CTTheme.color.textOnSurfacePrimary,
            backgroundColor = Color.Transparent,
            onClick = onClickDelete,
        )
    }
}

@Composable
private fun ContentCard(
    steps: List<MapRowPickerState>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CTTheme.color.surface),
    ) {
        steps.mapIndexed { index, step ->
            val (paddingTop, paddingBot) = when (index) {
                0 -> CTTheme.spacing.medium to CTTheme.spacing.small
                steps.size - 1 -> CTTheme.spacing.small to CTTheme.spacing.medium
                else -> CTTheme.spacing.small to CTTheme.spacing.small
            }
            step.Content(
                paddingValues = PaddingValues(
                    top = paddingTop,
                    bottom = paddingBot,
                    start = CTTheme.spacing.medium,
                    end = CTTheme.spacing.medium,
                )
            )
        }
    }
}

@Stable
data class CrewStepsCardState(
    val crewName: TextSpec,
    val steps: List<MapRowPickerState>,
    val onClickAddStep: () -> Unit,
    val onClickEdit: () -> Unit,
    val onClickDelete: () -> Unit,
) {
    @Composable
    fun Content(modifier: Modifier = Modifier) {
        CrewStepsCard(
            crewName = crewName,
            steps = steps,
            onClickAddStep = onClickAddStep,
            onClickEdit = onClickEdit,
            onClickDelete = onClickDelete,
            modifier = modifier,
        )
    }

    fun lazyItem(
        scope: LazyListScope,
        modifier: Modifier = Modifier,
    ) {
        scope.item(
            contentType = contentType,
            key = crewName.hashCode(),
        ) {
            Content(modifier = modifier)
        }
    }

    private companion object {
        const val contentType: String = "CrewStepsCard"
    }
}

@Composable
@Preview
private fun PreviewCrewStepsCard() {
    CTTheme(CTColorTheme.Cartography) {
        CrewStepsCard(
            crewName = "Membre 1".textSpec(),
            steps = listOf(
                MapRowPickerState(
                    uiMarker = UIMarker(
                        coordinates = Coordinates(45.0, 4.0),
                        iconMarker = CacheMarkerIcon.Empty("", CTTheme.color.primary),
                        isSelected = false,
                    ),
                    text = "Étape 1".textSpec(),
                    key = 1,
                    onClick = {},
                ),
                MapRowPickerState(
                    uiMarker = UIMarker(
                        coordinates = Coordinates(45.0, 4.0),
                        iconMarker = CacheMarkerIcon.Empty("", CTTheme.color.primary),
                        isSelected = false,
                    ),
                    text = "Étape 2".textSpec(),
                    key = 2,
                    onClick = {},
                ),
                MapRowPickerState(
                    uiMarker = null,
                    text = "Étape 3".textSpec(),
                    key = 3,
                    onClick = {},
                )
            ),
            onClickAddStep = {},
            onClickEdit = {},
            onClickDelete = {},
        )
    }
}
