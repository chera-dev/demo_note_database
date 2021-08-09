package com.example.demonotedatabase

import androidx.lifecycle.LiveData

class LabelRepository(private val labelDao: LabelDao) {

    val readAllLabel: LiveData<List<Label>> = labelDao.readAllLabel()

    suspend fun addLabel(label: Label){
        labelDao.addLabel(label)
    }

    suspend fun updateLabel(label: Label){
        labelDao.updateLabel(label)
    }

    suspend fun deleteLabel(label: Label){
        labelDao.deleteLabel(label)
    }


}