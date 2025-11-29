package com.kaushalvasava.apps.taskapp.ui.admin.departments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import comkaushalvasavaappstaskapp.Department

@Composable
fun AddEditDepartmentDialog(
    initial: Department?,
    onDismiss: () -> Unit,
    onSave: (Department) -> Unit
) {
    var name by remember { mutableStateOf(initial?.dept_name ?: "") }
    var location by remember { mutableStateOf(initial?.dept_location ?: "") }
    var storeId by remember { mutableStateOf(initial?.deptStoreId?.toString() ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(if (initial == null) "Add Department" else "Edit Department")
        },
        text = {
            Column {

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Department Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = storeId,
                    onValueChange = { storeId = it },
                    label = { Text("Store ID") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val updated = Department(
                    dept_id = initial?.dept_id ?: 0L,
                    dept_name = name,
                    dept_location = location,
                    deptStoreId = storeId.toLongOrNull() ?: 0L
                )
                onSave(updated)
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
