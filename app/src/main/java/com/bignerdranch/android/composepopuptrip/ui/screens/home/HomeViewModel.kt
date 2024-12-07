package com.bignerdranch.android.composepopuptrip.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.composepopuptrip.data.repository.GooglePlacesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GooglePlacesRepository) : ViewModel() {

    private val _startQuery = MutableStateFlow("")
    val startQuery: StateFlow<String> get() = _startQuery

    private val _destQuery = MutableStateFlow("")
    val destQuery: StateFlow<String> get() = _destQuery

    private val _suggestions = MutableStateFlow<List<String>>(emptyList())
    val suggestions: StateFlow<List<String>> get() = _suggestions

    fun updateStartQuery(newQuery: String) {
        _startQuery.value = newQuery
        if (newQuery.isNotEmpty()) {
            fetchPlaceSuggestions(newQuery)
        } else {
            _suggestions.value = emptyList()
        }
    }

    fun updateQuery(newQuery: String) {
        _destQuery.value = newQuery
        if (newQuery.isNotEmpty()) {
            fetchPlaceSuggestions(newQuery)
        } else {
            _suggestions.value = emptyList()
        }
    }

    private fun fetchPlaceSuggestions(query: String) {
        viewModelScope.launch {
            repository.fetchPlaceSuggestions(
                query = query,
                onSuccess = { suggestions ->
                    _suggestions.value = suggestions
                },
                onError = {
                    _suggestions.value = emptyList()
                }
            )
        }
    }
}
