package com.bignerdranch.android.composepopuptrip.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.composepopuptrip.data.repository.GooglePlacesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GooglePlacesRepository) : ViewModel() {

    // State for search query
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    // State for autocomplete suggestions
    private val _suggestions = MutableStateFlow<List<String>>(emptyList())
    val suggestions: StateFlow<List<String>> get() = _suggestions

    // Update query and fetch suggestions
    fun updateQuery(newQuery: String) {
        _query.value = newQuery
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
