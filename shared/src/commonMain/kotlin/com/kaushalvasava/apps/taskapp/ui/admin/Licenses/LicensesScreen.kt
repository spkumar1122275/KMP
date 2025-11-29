package com.kaushalvasava.apps.taskapp.ui.admin.licenses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.kaushalvasava.apps.taskapp.viewmodel.LicensesViewModel
import comkaushalvasavaappstaskapp.Licenses


@Composable
fun LicensesScreen(
    viewModel: LicensesViewModel,
    companyViewModel: CompanyViewModel
) {
    val licenses by viewModel.licenses.collectAsState()
    val companies by companyViewModel.companies.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var licenseToEdit by remember { mutableStateOf<Licenses?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadLicenses()
        companyViewModel.loadCompanies()
    }

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
