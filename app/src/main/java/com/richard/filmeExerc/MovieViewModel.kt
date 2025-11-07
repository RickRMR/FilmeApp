package com.richard.filmeExerc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.richard.filmeExerc.placeholder.PlaceholderContent

@Suppress("UNCHECKED_CAST")
class MovieViewModel: ViewModel() {

    val movieDetailsLiveData: LiveData<MovieDetails>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<MovieDetails>()

    val movieListLiveData: LiveData<List<PlaceholderContent.PlaceholderItem>>
        get() = _movieListLiveData
    private val _movieListLiveData: LiveData<List<PlaceholderContent.PlaceholderItem>> =
        MutableLiveData<MutableLiveData<PlaceholderContent.PlaceholderItem>>() as LiveData<List<PlaceholderContent.PlaceholderItem>>

    val navigationToDetailLiveData
        get()=_navigationToDetailLiveData
    private val _navigationToDetailLiveData = MutableLiveData<Unit>()

    init {
        _movieListLiveData.postValue(PlaceholderContent.ITEMS as MutableLiveData<PlaceholderContent.PlaceholderItem>?)
    }

    fun onMovieSelected(position: Int) {
        val movieDetails = MovieDetails("Meu filme", "Conteúdo de teste")
        _movieDetailsLiveData.postValue(movieDetails)
        _navigationToDetailLiveData.postValue(Unit)
    }
}

private fun LiveData<List<PlaceholderContent.PlaceholderItem>>.postValue(value: MutableLiveData<PlaceholderContent.PlaceholderItem>?) {}
