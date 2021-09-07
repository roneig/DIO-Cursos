package br.com.dio.businesscard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val dao: BusinessCardDao) {

    fun getAll() = dao.getAll()

    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(businessCard)
        }
    }

    fun delOne(businessCard: BusinessCard) =  runBlocking {
        launch (Dispatchers.IO) {
            dao.delOne(businessCard)
        }
    }

    fun editOne(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.editOne(businessCard)
        }
    }
}