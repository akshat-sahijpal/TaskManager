package com.akshatsahijpal.clockwat.ui.HomeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akshatsahijpal.clockwat.data.Todo
import com.akshatsahijpal.clockwat.ui.TodoViewModel.DeleteTodo
import com.akshatsahijpal.clockwat.ui.TodoViewModel.OnToggleDoneState
import com.akshatsahijpal.clockwat.ui.TodoViewModel.TodoEvents

@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier,
    onEvent: (TodoEvents) -> Unit,
) {
    Row(modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = todo.TaskName, style = TextStyle(color = Color.White, fontSize = 15.sp))
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { onEvent(DeleteTodo(todo = todo)) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }
            Text(text = todo.userName, style = TextStyle(color = Color.White, fontSize = 15.sp))

        }
        Checkbox(checked = todo.isDone, onCheckedChange = {
            onEvent(OnToggleDoneState(todo, it))
        })
    }
}