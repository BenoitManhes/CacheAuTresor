package com.benoitmanhes.designsystem.molecule.selector

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Stable
interface SelectorState {
    val selectedIndex: Float
    val itemsContentColor: List<Color>

    fun selectedItem(scope: CoroutineScope, index: Int)
}

@Stable
class SelectorStateImpl(
    items: List<SelectorItem>,
    selectedItem: SelectorItem,
    private val selectedColor: Color,
    private val unselectedColor: Color,
) : SelectorState {

    override val selectedIndex: Float
        get() = _selectedIndex.value
    private var _selectedIndex = Animatable(items.indexOf(selectedItem).toFloat())

    override val itemsContentColor: List<Color>
        get() = _itemsContentColor.value
    private var _itemsContentColor: State<List<Color>> = derivedStateOf {
        List(items.size) { index ->
            lerp(
                start = unselectedColor,
                stop = selectedColor,
                fraction = 1f - (((selectedIndex - index.toFloat()).absoluteValue).coerceAtMost(1f))
            )
        }
    }

    private val animationSpec = tween<Float>(
        durationMillis = 300,
        easing = FastOutSlowInEasing,
    )

    override fun selectedItem(scope: CoroutineScope, index: Int) {
        scope.launch {
            _selectedIndex.animateTo(
                targetValue = index.toFloat(),
                animationSpec = animationSpec,
            )
        }
    }
}

@Composable
fun rememberSelectorState(
    items: List<SelectorItem>,
    selectedItem: SelectorItem,
    selectedColor: Color,
    unselectedColor: Color,
): SelectorStateImpl = remember(items, selectedColor, unselectedColor) {
    SelectorStateImpl(
        items,
        selectedItem,
        selectedColor,
        unselectedColor,
    )
}
