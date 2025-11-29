package com.kaushalvasava.apps.taskapp.ui.admin.departments


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaushalvasava.apps.taskapp.viewmodel.CompanyViewModel
import com.kaushalvasava.apps.taskapp.viewmodel.DepartmentViewModel
import comkaushalvasavaappstaskapp.Department

@Composable
fun DepartmentsScreen(departmentViewModel: DepartmentViewModel, companyViewModel: CompanyViewModel) {

    val departments by departmentViewModel.departments.collectAsState()
    val companies by companyViewModel.companies.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var deptToEdit by remember { mutableStateOf<Department?>(null) }
    var deptToDelete by remember { mutableStateOf<Department?>(null) }

    LaunchedEffect(Unit) {
        departmentViewModel.loadDepartments()
        companyViewModel.loadCompanies()
    }

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
            companies = companies,
            onDismiss = { showDialog = false },
            onSave = { data ->
                if (deptToEdit == null) departmentViewModel.addDepartment(data)
                else departmentViewModel.updateDepartment(data)
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
                    departmentViewModel.deleteDepartment(deptToDelete!!.dept_id)
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