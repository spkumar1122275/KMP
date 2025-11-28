package com.kaushalvasava.apps.taskapp.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Sidebar(
    currentPage: AdminPage,
    onPageSelected: (AdminPage) -> Unit
) {
    Column(
        modifier = Modifier
            .width(220.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colors.surface)
            .padding(16.dp)
    ) {
        Text(
            "Admin Panel",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SidebarItem("Companies", AdminPage.Companies, currentPage, onPageSelected)
        SidebarItem("Departments", AdminPage.Departments, currentPage, onPageSelected)
        SidebarItem("Licenses", AdminPage.Licenses, currentPage, onPageSelected)
        SidebarItem("Main Users", AdminPage.MainUsers, currentPage, onPageSelected)
        SidebarItem("Terminal Users", AdminPage.TerminalUsers, currentPage, onPageSelected)
        SidebarItem("Assign Licenses", AdminPage.AssignLicenses, currentPage, onPageSelected)
    }
}

@Composable
private fun SidebarItem(
    label: String,
    page: AdminPage,
    currentPage: AdminPage,
    onClick: (AdminPage) -> Unit
) {
    val isSelected = currentPage == page

    Surface(
        color = if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.15f)
        else MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick(page) }
    ) {
        Text(
            label,
            modifier = Modifier.padding(12.dp),
            color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
        )
    }
}
