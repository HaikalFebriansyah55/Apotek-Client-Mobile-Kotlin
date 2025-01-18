package com.example.uas.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ObatViewModel : ViewModel() {

    private val _obatList = MutableLiveData<List<ObatResponseItem>>()
    val obatList: LiveData<List<ObatResponseItem>> get() = _obatList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        fetchObat()
    }

    fun fetchObat() {
        _isLoading.value = true
        _errorMessage.value = ""
        viewModelScope.launch {
            try {
                val response = ApiClient.crudService.getAllObatData()
                if (response.isNotEmpty()) {
                    _obatList.value = response
                    response.forEach {
                        Log.d("Alex", "fetchObat: ${it.nama}, ${it.harga}, ${it.jenis}, ${it.deskripsi}, ${it.gambar}")
                    }
                } else {
                    _errorMessage.value = "Failed to load Obat Data"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown Error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}