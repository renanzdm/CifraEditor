package com.renan.cifraeditor.data.mappers

import com.renan.cifraeditor.data.tables.Cipher
import com.renan.cifraeditor.domain.entities.CipherEntity


fun CipherEntity.toCipher() : Cipher{
    return Cipher(
        id = id,
        name = name,
        artist = artist,
        fkTom = fkTom
    )
}