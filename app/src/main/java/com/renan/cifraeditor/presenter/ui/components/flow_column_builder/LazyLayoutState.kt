package com.renan.cifraeditor.presenter.ui.components.flow_column_builder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset

@Composable
fun rememberLazyLayoutState(): LazyLayoutState {
    return remember { LazyLayoutState() }
}

@Stable
class LazyLayoutState {

    private val _offsetState = mutableStateOf(IntOffset(0, 0))
    val offsetState = _offsetState

    fun onDrag(offset: IntOffset) {
        val x = (_offsetState.value.x - offset.x).coerceAtLeast(0)
        val y = (_offsetState.value.y - offset.y).coerceAtLeast(0)
        _offsetState.value = IntOffset(x, y)
    }

    fun getBoundaries(
        constraints: Constraints,
    ): ViewBoundaries {
        return ViewBoundaries(
            fromX = offsetState.value.x ,
            toX = constraints.maxWidth,
            fromY = offsetState.value.y ,
            toY = constraints.maxHeight + offsetState.value.y
        )
    }
}