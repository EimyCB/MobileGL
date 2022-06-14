package com.example.mobile_gl.data

import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remote: MainDataSource
) {
    suspend fun getList() =
        remote.getList()
}