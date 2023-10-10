package com.renan.cifraeditor.data.services.daos

import com.renan.cifraeditor.data.tables.Tom
import com.renan.cifraeditor.domain.services.daos.TomDao

class TomDaoImpl: TomDao {
    override suspend fun getAll(): List<Tom> {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllByIds(ids: IntArray): List<Tom> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(toms: Tom) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(tom: Tom) {
        TODO("Not yet implemented")
    }

}