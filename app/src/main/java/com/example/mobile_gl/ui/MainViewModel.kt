package com.example.mobile_gl.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_gl.data.MainRepository
import com.example.mobile_gl.data.Resource
import com.example.mobile_gl.data.model.MainResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _listResult = MutableLiveData<Resource<List<MainResponse>>>()
    val listResult: LiveData<Resource<List<MainResponse>>> = _listResult

    fun onStart() {
        getMainResponse()
    }
    fun getMainResponse() = viewModelScope.launch(Dispatchers.Main) {
        _listResult.value = Resource.loading()
        val response = withContext(Dispatchers.IO){
            repository.getList()
        }
        _listResult.value = response
    }
}