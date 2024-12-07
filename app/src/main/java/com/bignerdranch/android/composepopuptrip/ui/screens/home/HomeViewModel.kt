package com.bignerdranch.android.composepopuptrip.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.composepopuptrip.data.repository.GooglePlacesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GooglePlacesRepository) : ViewModel() {

    // State for starting point search query
    private val _startQuery = MutableStateFlow("")
    val startQuery: StateFlow<String> get() = _startQuery

    // State for search query
    private val _destQuery = MutableStateFlow("")
    val destQuery: StateFlow<String> get() = _destQuery

    // State for autocomplete suggestions
    private val _suggestions = MutableStateFlow<List<String>>(emptyList())
    val suggestions: StateFlow<List<String>> get() = _suggestions

    // Update the starting point query
    fun updateStartQuery(newQuery: String) {
        _startQuery.value = newQuery
        if (newQuery.isNotEmpty()) {
            fetchPlaceSuggestions(newQuery)
        } else {
            _suggestions.value = emptyList()
        }
    }

    // Update query and fetch suggestions
    fun updateQuery(newQuery: String) {
        _destQuery.value = newQuery
        if (newQuery.isNotEmpty()) {
            fetchPlaceSuggestions(newQuery)
        } else {
            _suggestions.value = emptyList()
        }
    }

    // Fetch suggestions from the repository
    private fun fetchPlaceSuggestions(query: String) {
        viewModelScope.launch {
            repository.fetchPlaceSuggestions(
                query = query,
                onSuccess = { suggestions ->
                    _suggestions.value = suggestions
                },
                onError = {
                    _suggestions.value = emptyList() // Clear suggestions on error
                }
            )
        }
    }
}
