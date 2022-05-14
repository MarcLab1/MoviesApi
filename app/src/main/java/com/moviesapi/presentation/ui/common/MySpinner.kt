package com.moviesapi.presentation.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moviesapi.Constants.movietypes

@Composable
fun MySpinner(selectedType : MutableState<String>, OnFilterChangedEvent:() -> Unit)
{   var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, MaterialTheme.colors.primary),
                shape = RoundedCornerShape(10)
            )
            .padding(all = 10.dp)
    )
    {
        Text("Type: ")
        Text(selectedType.value)
        Box {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .clickable {
                        expanded = true
                    }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                movietypes.forEach { item ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        selectedType.value = item
                        OnFilterChangedEvent()
                    })
                    {
                        Text(text = item.toString())
                    }
                }
            }
        }
    }
}