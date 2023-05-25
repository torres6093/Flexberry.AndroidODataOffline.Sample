package com.flexberry.androidodataofflinesample.ui.detaillistform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexberry.androidodataofflinesample.ApplicationState
import com.flexberry.androidodataofflinesample.data.DetailRepository
import com.flexberry.androidodataofflinesample.data.di.AppState
import com.flexberry.androidodataofflinesample.data.model.Detail
import com.flexberry.androidodataofflinesample.ui.navigation.AppNavigator
import com.flexberry.androidodataofflinesample.ui.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailListFormViewModel@Inject constructor(
    private val repository: DetailRepository,
    @AppState private val applicationState: ApplicationState,
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyModelUiState.Success(emptyList()))
    val uiState: StateFlow<MyModelUiState> = _uiState

    // для вставки данных в room db
    val testDataInsert = repository.insertDetailOffline()

    init {
        viewModelScope.launch {
            if (applicationState.isOnline.value)
                repository.getDetailsOnline().collect { details ->
                    _uiState.value = MyModelUiState.Success(details)
                }
            else repository.getDetailsOffline().collect { details ->
                _uiState.value = MyModelUiState.Success(details)
            }

        }
    }

    fun onAddDetailButtonClicked():Unit {
        // добавление нового пользователя
    }

    fun onEditDetailClicked():Unit {
        // изменение данных пользователя
    }

    fun onDeleteDetailClicked():Unit {
        // удаление пользователя
    }

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack(Destination.MainScreen())
    }
}

sealed interface MyModelUiState {
    object Loading : MyModelUiState
    data class Error(val throwable: Throwable) : MyModelUiState
    data class Success(val data: List<Detail>) : MyModelUiState
}