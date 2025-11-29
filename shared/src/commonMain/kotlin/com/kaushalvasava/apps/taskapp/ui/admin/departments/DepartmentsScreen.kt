package com.kaushalvasava.apps.taskapp.ui.admin.departments


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import comkaushalvasavaappstaskapp.Department
import com.kaushalvasava.apps.taskapp.viewmodel.DepartmentViewModel

@Composable
fun DepartmentsScreen(viewModel: DepartmentViewModel) {

    val departments by viewModel.departments.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var deptToEdit by remember { mutableStateOf<Department?>(null) }
    var deptToDelete by remember { mutableStateOf<Department?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Departments", style = MaterialTheme.typography.h5)

            Button(onClick = {
                deptToEdit = null
                showDialog = true
            }) {
                Text("Add Department")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(departments) { dept ->
                DepartmentItem(
                    department = dept,
                    onEdit = {
                        deptToEdit = dept
                        showDialog = true
                    },
                    onDelete = { deptToDelete = dept }
                )
            }
        }
    }

    if (showDialog) {
        AddEditDepartmentDialog(
            initial = deptToEdit,
            onDismiss = { showDialog = false },
            onSave = { data ->
                if (deptToEdit == null) viewModel.addDepartment(data)
                else viewModel.updateDepartment(data)
                showDialog = false
            }
        )
    }

    if (deptToDelete != null) {
        AlertDialog(
            onDismissRequest = { deptToDelete = null },
            title = { Text("Delete Department?") },
            text = {
                Text("Are you sure you want to delete this department?")
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteDepartment(deptToDelete!!.dept_id)
                    deptToDelete = null
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { deptToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}
