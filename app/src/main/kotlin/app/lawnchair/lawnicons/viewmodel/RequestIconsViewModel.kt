package app.lawnchair.lawnicons.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.lawnchair.lawnicons.repository.IconRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RequestIconsViewModel @Inject constructor(private val iconRepository: IconRepository) :
    ViewModel() {
    val requestedIcons = iconRepository.requestedIconList
    fun getRequestedIcons() {
        viewModelScope.launch {
            iconRepository.getRequestedIcons()
        }
    }
}

