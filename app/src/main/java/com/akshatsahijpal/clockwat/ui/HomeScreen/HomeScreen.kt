package com.akshatsahijpal.clockwat.ui.HomeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.akshatsahijpal.clockwat.ui.TodoViewModel.OnAddTodo
import com.akshatsahijpal.clockwat.ui.TodoViewModel.OnTodoClick
import com.akshatsahijpal.clockwat.ui.TodoViewModel.OnUndoTodo
import com.akshatsahijpal.clockwat.ui.TodoViewModel.TodoViewModel
import com.akshatsahijpal.clockwat.util.Navigate
import com.akshatsahijpal.clockwat.util.ShowSnackBar
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@OptIn(InternalCoroutinesApi::class)
@Composable
fun HomeScreen(
    onNavigate: (Navigate) -> Unit,
    todoViewModel: TodoViewModel = hiltViewModel()
) {
    var scafState = rememberScaffoldState()
    var todos = todoViewModel.todos.collectAsState(initial = emptyList())
    // For SideEffects
    LaunchedEffect(key1 = true) {

        todoViewModel.uiEvents.collect {
            when (it) {
                is ShowSnackBar -> {
                    var result = scafState.snackbarHostState.showSnackbar(
                        message = it.message!!,
                        actionLabel = it.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        todoViewModel.onEvent(OnUndoTodo)
                    }
                }
                is Navigate -> {
                    onNavigate(it)
                }
                else -> Unit
            }
        }
    }
    Scaffold(scaffoldState = scafState, floatingActionButton = {
        FloatingActionButton(onClick = { todoViewModel.onEvent(OnAddTodo) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(todos.value) { todo ->
                TodoItem(todo = todo, modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        todoViewModel.onEvent(OnTodoClick(todo))
                    }, onEvent = todoViewModel::onEvent
                )
            }
        }
    }
}