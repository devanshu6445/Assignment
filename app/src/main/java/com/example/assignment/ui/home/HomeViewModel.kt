package com.example.assignment.ui.home

import androidx.lifecycle.*
import com.example.assignment.database.AppDatabase
import com.example.assignment.database.ModelData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.withTestContext
import kotlinx.coroutines.withContext

class HomeViewModel(database: AppDatabase) : ViewModel() {

    private val repository = Repository(database)
    private val mutableList = MutableLiveData(listOf<ModelData>())
    val list:LiveData<List<ModelData>> = mutableList

    init {
        viewModelScope.launch {
            repository.getAllData().collect{
                withContext(Dispatchers.Main){
                    mutableList.value = it
                }
            }
        }
    }
    fun insertData(data:ModelData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(data)
        }
    }
}

class HomeViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(database) as T
        else
            throw IllegalStateException("Unknown error")
    }
}