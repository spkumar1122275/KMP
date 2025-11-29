package com.kaushalvasava.apps.taskapp.ui.admin.departments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import comkaushalvasavaappstaskapp.Company
import comkaushalvasavaappstaskapp.Department

@Composable
fun AddEditDepartmentDialog(
    initial: Department?,
    companies: List<Company>,
    onDismiss: () -> Unit,
    onSave: (Department) -> Unit
) {
    var name by remember { mutableStateOf(initial?.dept_name ?: "") }
    var location by remember { mutableStateOf(initial?.dept_location ?: "") }

    // store_id selection
    var selectedCompany by remember {
        mutableStateOf(
            companies.firstOrNull { it.store_id == initial?.deptStoreId }
                ?: companies.firstOrNull()
        )
    }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(if (initial == null) "Add Department" else "Edit Department")
        },
        text = {
            Column(Modifier.fillMaxWidth()) {

                // Company Dropdown
                OutlinedTextField(
                    readOnly = true,
                    value = selectedCompany?.company_name ?: "Select Company",
                    onValueChange = {},
                    label = { Text("Company") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, null)
                        }
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    companies.forEach { company ->
                        DropdownMenuItem(onClick = {
                            selectedCompany = company
                            expanded = false
                        }) {
                            Text(company.company_name ?: "")
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

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
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val updated = Department(
                    dept_id = initial?.dept_id ?: 0L,
                    dept_name = name,
                    dept_location = location,
                    deptStoreId = selectedCompany?.store_id ?: 0L
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