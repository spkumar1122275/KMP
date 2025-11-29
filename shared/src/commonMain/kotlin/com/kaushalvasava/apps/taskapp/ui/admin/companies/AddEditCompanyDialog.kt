package com.kaushalvasava.apps.taskapp.ui.admin.companies

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import comkaushalvasavaappstaskapp.Company

@Composable
fun AddEditCompanyDialog(
    initial: Company?,
    onDismiss: () -> Unit,
    onSave: (Company) -> Unit
) {
    var companyId by remember { mutableStateOf(initial?.company_id?.toString() ?: "") }
    var name by remember { mutableStateOf(initial?.company_name ?: "") }
    var address by remember { mutableStateOf(initial?.address ?: "") }
    var phone by remember { mutableStateOf(initial?.phone ?: "") }
    var taxId by remember { mutableStateOf(initial?.tax_id ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(if (initial == null) "Add Company" else "Edit Company")
        },
        text = {
            Column(Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = companyId,
                    onValueChange = { companyId = it },
                    label = { Text("Company ID") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Company Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Address") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = taxId,
                    onValueChange = { taxId = it },
                    label = { Text("Tax ID") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val finalCompany = Company(
                    store_id = initial?.store_id ?: 0L,   // AUTOHANDLED IN DB
                    company_id = companyId.toLongOrNull(),
                    company_name = name,
                    address = address,
                    phone = phone,
                    tax_id = taxId
                )
                onSave(finalCompany)
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
