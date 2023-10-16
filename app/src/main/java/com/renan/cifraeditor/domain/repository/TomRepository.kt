package com.renan.cifraeditor.domain.repository

import com.renan.cifraeditor.domain.entities.TomEntity
import com.renan.cifraeditor.utils.Resource

interface TomRepository {
    suspend fun getAll(): Resource<List<TomEntity>>


}