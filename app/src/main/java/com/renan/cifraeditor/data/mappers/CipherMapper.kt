package com.renan.cifraeditor.data.mappers

import com.renan.cifraeditor.data.tables.Cipher
import com.renan.cifraeditor.domain.entities.CipherEntity


fun CipherEntity.toCipher(): Cipher {
    return Cipher(
        id = id, name = name, artist = artist, fkTom = fkTom
    )
}

fun Cipher.toEntity(): CipherEntity {
    return CipherEntity(
        id = id, name = name, artist = artist, fkTom = fkTom
    )
}


fun List<Cipher>.toEntities(): List<CipherEntity> {
    return map {
        CipherEntity(
            id = it.id, name = it.name, artist = it.artist, fkTom = it.fkTom
        )

    }
}