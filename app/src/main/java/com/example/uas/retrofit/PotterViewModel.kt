package com.example.uas.retrofit

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PotterViewModel : ViewModel() {

    private val _characters = MutableLiveData<List<ResponseItem>>()
    val characters: LiveData<List<ResponseItem>> get() = _characters

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        fetchCharacters()
    }

    fun fetchCharacters(){
        _isLoading.value = true
        _errorMessage.value = ""
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getAllCharacterData()
                if (response != null){
                    _characters.value = response
                    Log.d("alex", "fetchCharacters: ${response.size} items")
                } else {
                    _errorMessage.value = "Failed to load Character Data"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown Error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}