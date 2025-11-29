package com.kaushalvasava.apps.taskapp.ui.admin.licenses

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import comkaushalvasavaappstaskapp.Licenses
import comkaushalvasavaappstaskapp.Company
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.ArrowDropDown

@Composable
fun AddEditLicenseDialog(
    initial: Licenses?,
    companies: List<Company>,
    onDismiss: () -> Unit,
    onSave: (Licenses) -> Unit
) {
    var name by remember { mutableStateOf(initial?.license_name ?: "") }
    var refNo by remember { mutableStateOf(initial?.license_ref_no ?: "") }
    var validTill by remember { mutableStateOf(initial?.valid_till ?: "") }

    // store_id selection
    var selectedCompany by remember {
        mutableStateOf(
            companies.firstOrNull { it.store_id == initial?.store_id }
                ?: companies.firstOrNull()
        )
    }

    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initial == null) "Add License" else "Edit License") },
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
                    label = { Text("License Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = refNo,
                    onValueChange = { refNo = it },
                    label = { Text("Reference No.") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = validTill,
                    onValueChange = { validTill = it },
                    label = { Text("Valid Till") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                selectedCompany?.let { company ->
                    onSave(
                        Licenses(
                            license_id = initial?.license_id ?: 0,
                            store_id = company.store_id,
                            license_name = name,
                            license_ref_no = refNo,
                            valid_till = validTill
                        )
                    )
                }
            }) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

