package com.akshatsahijpal.clockwat.ui.EditScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.akshatsahijpal.clockwat.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel
@Inject constructor(
    private var repo: TodoRepository,
    savedStateHandle: SavedStateHandle // k-v for states: saves things like id nav c etc
) : ViewModel() {

}