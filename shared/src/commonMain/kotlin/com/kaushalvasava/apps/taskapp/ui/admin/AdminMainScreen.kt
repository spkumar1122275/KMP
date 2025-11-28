package com.kaushalvasava.apps.taskapp.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminMainScreen() {
    var currentPage by remember { mutableStateOf<AdminPage>(AdminPage.Companies) }

    Row(modifier = Modifier.fillMaxSize()) {

        // Sidebar
        Sidebar(
            currentPage = currentPage,
            onPageSelected = { currentPage = it }
        )

        // Main Content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            when (currentPage) {
                AdminPage.Companies -> PlaceholderPage("Companies Page")
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
