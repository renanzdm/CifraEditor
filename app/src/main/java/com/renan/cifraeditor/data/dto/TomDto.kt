package com.renan.cifraeditor.data.dto

import com.renan.cifraeditor.data.tables.Tom
import com.renan.cifraeditor.domain.entities.TomEntity

fun Tom.toEntity(): TomEntity {
    return TomEntity(
        id = id, name = name
    )
}

fun List<Tom>.toEntity(): List<TomEntity> {
    return this.map { tom -> tom.toEntity() }
}