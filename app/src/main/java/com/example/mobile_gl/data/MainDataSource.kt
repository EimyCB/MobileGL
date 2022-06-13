package com.example.mobile_gl.data

import javax.inject.Inject

class MainDataSource @Inject constructor(private val apiService: ApiService?): BaseDataSource() {
    suspend fun getList() = getResult { apiService?.list() }
}