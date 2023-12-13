package com.renan.cifraeditor.presenter.ui.components.flow_column_builder

import com.renan.cifraeditor.domain.entities.entitiesrelations.WordWithChords

interface CustomLazyListScope {

    fun items(items: List<WordWithChords>, itemContent: ComposableItemContent)
}

class CustomLazyListScopeImpl : CustomLazyListScope {

    private val _items = mutableListOf<LazyLayoutItemContent>()
    val items: List<LazyLayoutItemContent> = _items

    override fun items(items: List<WordWithChords>, itemContent: ComposableItemContent) {
        repeat(items.size) { _items.add(LazyLayoutItemContent(itemContent = itemContent)) }
    }
}