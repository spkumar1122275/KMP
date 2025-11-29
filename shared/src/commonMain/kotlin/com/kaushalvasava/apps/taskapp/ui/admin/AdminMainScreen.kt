package com.kaushalvasava.apps.taskapp.ui.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.sqldelight.db.SqlDriver
import com.kaushalvasava.apps.taskapp.ui.admin.companies.CompaniesScreen
import com.kaushalvasava.apps.taskapp.ui.admin.departments.DepartmentsScreen
import com.kaushalvasava.apps.taskapp.ui.admin.licenses.LicensesScreen
import com.kaushalvasava.apps.taskapp.ui.admin.mainuser.MainUserScreen
import com.kaushalvasava.apps.taskapp.viewmodel.CompanyViewModel
import com.kaushalvasava.apps.taskapp.viewmodel.DepartmentViewModel
import com.kaushalvasava.apps.taskapp.viewmodel.LicensesViewModel
import com.kaushalvasava.apps.taskapp.viewmodel.MainUserViewModel

@Composable
fun AdminMainScreen(
    companyViewModel: CompanyViewModel,
    departmentViewModel: DepartmentViewModel,
    licensesViewModel: LicensesViewModel,
    mainUserViewModel: MainUserViewModel,
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
                AdminPage.Companies -> CompaniesScreen(companyViewModel)
                AdminPage.Departments -> DepartmentsScreen(departmentViewModel, companyViewModel)
                AdminPage.Licenses -> LicensesScreen(licensesViewModel, companyViewModel)
                AdminPage.MainUsers -> MainUserScreen(
                    mainUserViewModel = mainUserViewModel,
                    companyViewModel = companyViewModel,
                    departmentViewModel = departmentViewModel,
                    currentUserRole = "admin"
                )
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
