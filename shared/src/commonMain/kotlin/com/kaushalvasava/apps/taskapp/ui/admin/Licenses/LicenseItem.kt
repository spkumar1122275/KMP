package com.kaushalvasava.apps.taskapp.ui.admin.licenses

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import comkaushalvasavaappstaskapp.Licenses

@Composable
fun LicenseItem(
    license: Licenses,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Name: ${license.license_name ?: ""}", style = MaterialTheme.typography.subtitle1)
                Text("Ref No: ${license.license_ref_no ?: ""}", style = MaterialTheme.typography.body2)
                Text("Valid Till: ${license.valid_till ?: ""}", style = MaterialTheme.typography.body2)
            }

            Row {
                TextButton(onClick = onEdit) {
                    Text("Edit")
                }
                TextButton(onClick = onDelete) {
                    Text("Delete", color = MaterialTheme.colors.error)
                }
            }
        }
    }
}
