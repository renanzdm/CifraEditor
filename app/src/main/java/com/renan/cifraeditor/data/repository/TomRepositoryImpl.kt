package com.renan.cifraeditor.data.repository

import com.renan.cifraeditor.data.dto.toEntity
import com.renan.cifraeditor.domain.entities.TomEntity
import com.renan.cifraeditor.domain.daos.TomDao
import com.renan.cifraeditor.domain.database.toms
import com.renan.cifraeditor.domain.repository.TomRepository
import com.renan.cifraeditor.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TomRepositoryImpl @Inject constructor(private val tomDao: TomDao):TomRepository {
   override suspend fun getAll(): Resource<List<TomEntity>> {
    return withContext(Dispatchers.IO) {
            try {
                val response =  tomDao.getAll()
                if(response.isEmpty()){
                    for (tom in toms) {
                        tomDao.insert(tom =tom)
                    }
                }
                return@withContext Resource.Success(data= tomDao.getAll().toEntity())
            } catch (e: Exception) {
                return@withContext Resource.Error(message = e.localizedMessage ?:"")
            }
        }
    }
}