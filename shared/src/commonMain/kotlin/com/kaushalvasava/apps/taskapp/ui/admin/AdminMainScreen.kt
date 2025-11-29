package com.kaushalvasava.apps.taskapp.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaushalvasava.apps.taskapp.viewmodel.CompanyViewModel
import com.kaushalvasava.apps.taskapp.viewmodel.DepartmentViewModel
import com.kaushalvasava.apps.taskapp.viewmodel.LicensesViewModel
import app.cash.sqldelight.db.SqlDriver
import com.kaushalvasava.apps.taskapp.ui.admin.companies.CompaniesScreen
import com.kaushalvasava.apps.taskapp.ui.admin.departments.DepartmentsScreen
import com.kaushalvasava.apps.taskapp.ui.admin.licenses.LicensesScreen
import com.kaushalvasava.apps.taskapp.ui.admin.Sidebar



@Composable
fun AdminMainScreen(
    companyViewModel: CompanyViewModel,
    departmentViewModel: DepartmentViewModel,
    licensesViewModel: LicensesViewModel,
    sqlDriver: SqlDriver
) {
    var currentPage by remember { mutableStateOf<AdminPage>(AdminPage.Companies) }
    var isSidebarExpanded by remember { mutableStateOf(true) }

    Row(modifier = Modifier.fillMaxSize()) {

        // Sidebar
        Sidebar(
            currentPage = currentPage,
            onPageSelected = { page -> currentPage = page },

            isExpanded = isSidebarExpanded,
            onToggleExpand = { isSidebarExpanded = !isSidebarExpanded }
        )

        // Main Content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            when (currentPage) {
                AdminPage.Companies -> CompaniesScreen(viewModel = companyViewModel)
                AdminPage.Departments -> DepartmentsScreen(viewModel = departmentViewModel)
                AdminPage.Licenses -> LicensesScreen(viewModel = licensesViewModel, companyViewModel = companyViewModel)
                AdminPage.MainUsers -> PlaceholderPage("Main Users Page")
                AdminPage.TerminalUsers -> PlaceholderPage("Terminal Users Page")
                AdminPage.AssignLicenses -> PlaceholderPage("Assign Licenses Page")
            }
        }
    }
}

@Composable
private fun PlaceholderPage(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h5
    )
}
