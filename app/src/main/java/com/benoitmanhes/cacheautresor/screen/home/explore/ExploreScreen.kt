package com.benoitmanhes.cacheautresor.screen.home.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.designsystem.lab.Selector
import com.benoitmanhes.designsystem.lab.SelectorItem
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonType
import com.benoitmanhes.designsystem.molecule.button.fabiconbutton.FabIconButton
import com.benoitmanhes.designsystem.res.icons.iconpack.Search
import com.benoitmanhes.designsystem.res.icons.iconpack.Filter
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ExploreRoute(
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var selectorScreenItem by remember { mutableStateOf(selectorItems.first()) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(PositionDefault, ZoomDefault)
    }

    LaunchedEffect(true) {
        viewModel.initLocationListener(context)
    }
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        if (selectorScreenItem == selectorItems.first()) {
            ExploreMapScreen(
                uiState = viewModel.uiState,
                cameraPositionState = cameraPositionState,
                updateMapPosition = viewModel::setMapPosition,
            )
        } else {

        }
    }

    Row(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = CTTheme.spacing.large, vertical = CTTheme.spacing.small)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FabIconButton(
            icon = IconSpec.VectorIcon(imageVector = CTTheme.icon.Search, contentDescription = null),
            onClick = { /*TODO*/ },
            type = FabButtonType.OUTLINED,
        )
        Selector(
            items = selectorItems,
            selectedItem = selectorScreenItem,
            onSelectedItem = { selectorScreenItem = it },
            modifier = Modifier
                .width(SelectorItemWidth * selectorItems.size),
        )
        FabIconButton(
            icon = IconSpec.VectorIcon(imageVector = CTTheme.icon.Filter, contentDescription = null),
            onClick = { /*TODO*/ },
            type = FabButtonType.OUTLINED,
        )
    }
}

private const val ZoomDefault: Float = 15f
private val PositionDefault: LatLng = LatLng(45.76, 4.83)
private val SelectorItemWidth: Dp = 72.dp

private val selectorItems: List<SelectorItem> = listOf(
    SelectorItem(TextSpec.Resources(R.string.exploreScreen_selectorItem_map)),
    SelectorItem(TextSpec.Resources(R.string.exploreScreen_selectorItem_list)),
)
