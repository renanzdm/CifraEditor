package com.renan.cifraeditor.presenter.ui.components.flow_column_builder

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints

@OptIn(ExperimentalFoundationApi::class)
class ItemProvider(
    private val itemsState: State<List<LazyLayoutItemContent>>
) : LazyLayoutItemProvider {
    override val itemCount
        get() = itemsState.value.size

    @Composable
    override fun Item(index: Int, key: Any) {
        val item = itemsState.value.getOrNull(index)
        item?.itemContent?.invoke(index)
    }


    fun getItemIndexesInRange(
        constraints: Constraints,
        measureScope: LazyLayoutMeasureScope
    ): List<Placeable> {
        val itemsIndexes = mutableListOf<Placeable>()
        var y = 0
        var x = 0
        repeat(itemCount) { index ->
            val placeable: Placeable = measureScope.measure(index, Constraints()).first()
            if (x + placeable.width >= constraints.maxWidth) {
                y += placeable.height
                x = 0
                itemsIndexes.add(placeable)
            } else {
                x += placeable.width
                itemsIndexes.add(placeable)
            }
            if (y > constraints.maxHeight) {
                return itemsIndexes
            }

        }
        return itemsIndexes
    }

}


typealias  ComposableItemContent = @Composable (index:Int) -> Unit

data class LazyLayoutItemContent(
    val itemContent: ComposableItemContent,
)

@Composable
fun rememberItemProvider(customLazyListScope: CustomLazyListScope.() -> Unit): ItemProvider {
    val customLazyListScopeState = remember { mutableStateOf(customLazyListScope) }.apply {
        value = customLazyListScope
    }

    return remember {
        ItemProvider(
            derivedStateOf {
                val layoutScope = CustomLazyListScopeImpl().apply(customLazyListScopeState.value)
                layoutScope.items
            }
        )
    }
}