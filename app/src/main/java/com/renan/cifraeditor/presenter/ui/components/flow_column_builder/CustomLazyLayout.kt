package com.renan.cifraeditor.presenter.ui.components.flow_column_builder

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomLazyLayout(
    modifier: Modifier = Modifier,
    state: LazyLayoutState = rememberLazyLayoutState(),
    content: CustomLazyListScope.() -> Unit
) {
    val itemProvider = rememberItemProvider(content)

    LazyLayout(itemProvider = { itemProvider },
        modifier
            .clipToBounds()
            .lazyLayoutPointerInput(state),
        measurePolicy = { constraints ->
            var y = 0 - state.offsetState.value.y
            val boundaries = state.getBoundaries(constraints = constraints)
            val indexedItems = itemProvider.getItemIndexesInRange(
                Constraints(
                    minWidth = boundaries.fromX,
                    minHeight = boundaries.fromY,
                    maxHeight = boundaries.toY,
                    maxWidth = boundaries.toX
                ), this@LazyLayout
            )

            layout(constraints.maxWidth, constraints.maxHeight) {
                var x = 0
                indexedItems.forEach {
                    if (x + it.width >= constraints.maxWidth) {
                        y += it.height
                        x = 0
                        it.placeRelative(x, y)
                        x = it.width
                    } else {
                        it.placeRelative(x, y)
                        x += it.width
                    }
                }
            }
        }
    )
}


private fun Modifier.lazyLayoutPointerInput(state: LazyLayoutState): Modifier {
    return pointerInput(Unit) {
        detectDragGestures { change, dragAmount ->
            change.consume()
            state.onDrag(IntOffset(dragAmount.x.toInt(), dragAmount.y.toInt()))
        }
    }
}

