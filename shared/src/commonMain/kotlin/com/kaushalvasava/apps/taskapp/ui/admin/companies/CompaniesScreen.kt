package com.kaushalvasava.apps.taskapp.ui.admin.companies


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import comkaushalvasavaappstaskapp.Company
import com.kaushalvasava.apps.taskapp.viewmodel.CompanyViewModel

@Composable
fun CompaniesScreen(viewModel: CompanyViewModel) {
    val companies by viewModel.companies.collectAsState()

    var showAddEditDialog by remember { mutableStateOf(false) }
    var companyToEdit by remember { mutableStateOf<Company?>(null) }
    var showDeleteConfirm by remember { mutableStateOf<Company?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "Companies",
                style = MaterialTheme.typography.h5
            )

            Button(onClick = {
                companyToEdit = null
                showAddEditDialog = true
            }) {
                Text("Add Company")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(companies) { company ->
                CompanyItem(
                    company = company,
                    onEdit = {
                        companyToEdit = company
                        showAddEditDialog = true
                    },
                    onDelete = { showDeleteConfirm = company }
                )
            }
        }
    }

    if (showAddEditDialog) {
        AddEditCompanyDialog(
            initial = companyToEdit,
            onDismiss = { showAddEditDialog = false },
            onSave = { data ->
                if (companyToEdit == null) viewModel.addCompany(data)
                else viewModel.updateCompany(data)
                showAddEditDialog = false
            }
        )
    }

    if (showDeleteConfirm != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirm = null },
            title = { Text("Delete Company?") },
            text = { Text("Are you sure you want to delete this company?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteCompany(showDeleteConfirm!!.store_id)
                    showDeleteConfirm = null
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirm = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}
