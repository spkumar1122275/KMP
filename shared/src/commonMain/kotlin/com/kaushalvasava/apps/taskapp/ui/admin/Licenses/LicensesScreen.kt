package com.kaushalvasava.apps.taskapp.ui.admin.licenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import comkaushalvasavaappstaskapp.Licenses
import com.kaushalvasava.apps.taskapp.viewmodel.LicensesViewModel
import com.kaushalvasava.apps.taskapp.viewmodel.CompanyViewModel


@Composable
fun LicensesScreen(
    viewModel: LicensesViewModel,
    companyViewModel: CompanyViewModel
) {
    val licenses by viewModel.licenses.collectAsState()
    val companies by companyViewModel.companies.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var licenseToEdit by remember { mutableStateOf<Licenses?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Licenses", style = MaterialTheme.typography.h5)

            Button(onClick = {
                licenseToEdit = null
                showDialog = true
            }) { Text("Add License") }
        }

        LazyColumn {
            items(licenses) { license ->
                LicenseItem(
                    license = license,
                    onEdit = {
                        licenseToEdit = license
                        showDialog = true
                    },
                    onDelete = {
                        viewModel.deleteLicense(license.license_id, license.store_id ?: 0L)
                    }
                )
            }
        }
    }

    if (showDialog) {
        AddEditLicenseDialog(
            initial = licenseToEdit,
            companies = companies,
            onDismiss = { showDialog = false },
            onSave = {
                if (licenseToEdit == null) viewModel.addLicense(it)
                else viewModel.updateLicense(it)
                showDialog = false
            }
        )
    }
}
