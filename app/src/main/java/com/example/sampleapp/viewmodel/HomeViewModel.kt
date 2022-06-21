package com.example.sampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.models.TopHeadLines
import com.example.sampleapp.repo.DataProviderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: DataProviderRepository) : ViewModel() {

    private var _showProgress: MutableLiveData<Boolean> = MutableLiveData()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private var _isErrorOccrred: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorOccurred: LiveData<Boolean>
        get() = _isErrorOccrred

    val _topHeadLinesLiveData = MutableLiveData<TopHeadLines?>()
    val headlinesLiveData: MutableLiveData<TopHeadLines?>
        get() = _topHeadLinesLiveData

    fun fetchTopHeadlines() {
        _topHeadLinesLiveData.value = null
        _showProgress.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTopHeadLines()
                _showProgress.postValue(false)

                if (response.isSuccessful) {
                    val topHeadLinesData = response.body()
                    topHeadLinesData?.let {
                        _topHeadLinesLiveData.postValue(it)
                    }
                } else {
                    _isErrorOccrred.postValue(true)
                }
            } catch (exception: Exception) {
                _showProgress.postValue(false)
                _isErrorOccrred.postValue(true)
            }
        }
    }
}