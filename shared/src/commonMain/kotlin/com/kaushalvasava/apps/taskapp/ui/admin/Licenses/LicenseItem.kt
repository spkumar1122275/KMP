package com.kaushalvasava.apps.taskapp.ui.admin.licenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 12.dp)
                        .clickable { onEdit() }
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onDelete() }
                )
            }
        }
    }
}
