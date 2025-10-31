package com.richard.hqtestapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.richard.hqtestapp.placeholder.PlaceholderContent
import com.richard.hqtestapp.placeholder.PlaceholderContent.PlaceholderItem

class HQViewModel : ViewModel() {

    val hqDetailsLiveData: LiveData<HQDetails>
        get() = _hqDetailsLiveData
    private val _hqDetailsLiveData = MutableLiveData<HQDetails>()

    val hqListLiveData: LiveData<MutableLiveData<PlaceholderItem>>
        get() = _hqListLiveData
    private val _hqListLiveData = MutableLiveData<MutableLiveData<PlaceholderItem>>()

    val navigationToDetailLiveData
        get()=_navigationToDetailLiveData
    private val _navigationToDetailLiveData = MutableLiveData<Unit>()

    init {
        _hqListLiveData.postValue(PlaceholderContent.ITEMS as MutableLiveData<PlaceholderItem>?)
    }

    fun onHQSelected(position: Int) {
        val hqDetails = HQDetails("Minha HQ", "Conteúdo de teste")
        _hqDetailsLiveData.postValue(hqDetails)
        _navigationToDetailLiveData.postValue(Unit)
    }
}
