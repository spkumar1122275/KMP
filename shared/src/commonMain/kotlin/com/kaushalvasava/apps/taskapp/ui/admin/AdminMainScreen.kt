package com.kaushalvasava.apps.taskapp.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaushalvasava.apps.taskapp.viewmodel.CompanyViewModel
import app.cash.sqldelight.db.SqlDriver

@Composable
fun AdminMainScreen(
    companyViewModel: CompanyViewModel
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
                AdminPage.Departments -> PlaceholderPage("Departments Page")
                AdminPage.Licenses -> PlaceholderPage("Licenses Page")
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
