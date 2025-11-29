package com.kaushalvasava.apps.taskapp.ui.admin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
// ... other necessary imports
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.AssignmentInd

@Composable
fun Sidebar(
    currentPage: AdminPage,
    onPageSelected: (AdminPage) -> Unit,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit
) {
    // Animate the width of the sidebar smoothly
    val sidebarWidth by animateDpAsState(
        targetValue = if (isExpanded) 220.dp else 80.dp, // Collapsed width for icon only
        label = "sidebarWidthAnimation"
    )

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .width(sidebarWidth)
            .fillMaxHeight()
            .background(MaterialTheme.colors.surface)
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Only show the "Admin Panel" text if expanded
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    "Admin Panel",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            // Toggle button
            IconButton(onClick = onToggleExpand) {
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ArrowBack else Icons.Default.ArrowForward,
                    contentDescription = if (isExpanded) "Collapse Sidebar" else "Expand Sidebar"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sidebar items, passing the expanded state down
        SidebarItem("Companies", AdminPage.Companies, currentPage, onPageSelected, isExpanded)
        SidebarItem("Departments", AdminPage.Departments, currentPage, onPageSelected, isExpanded)
        SidebarItem("Licenses", AdminPage.Licenses, currentPage, onPageSelected, isExpanded)
        SidebarItem("Main Users", AdminPage.MainUsers, currentPage, onPageSelected, isExpanded)
        SidebarItem("Terminal Users", AdminPage.TerminalUsers, currentPage, onPageSelected, isExpanded)
        SidebarItem("Assign Licenses", AdminPage.AssignLicenses, currentPage, onPageSelected, isExpanded)
    }
}

@Composable
private fun SidebarItem(
    label: String,
    page: AdminPage,
    currentPage: AdminPage,
    onClick: (AdminPage) -> Unit,
    isSidebarExpanded: Boolean
) {
    val isSelected = currentPage == page

    // Helper function to get an appropriate icon for the page
    val icon = when (page) {
        AdminPage.Companies -> Icons.Default.Business
        AdminPage.Departments -> Icons.Default.Apartment
        AdminPage.Licenses -> Icons.Default.VerifiedUser // Example icon
        AdminPage.MainUsers -> Icons.Default.Group // Example icon
        AdminPage.TerminalUsers -> Icons.Default.Person // Example icon
        AdminPage.AssignLicenses -> Icons.Default.AssignmentInd // Example icon
        // Add more icons as needed
    }

    Surface(
        color = if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.15f)
        else MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick(page) }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // This is the placeholder example, now filled:
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface,
                modifier = Modifier.size(24.dp) // Standard icon size
            )

            // Only show text if the sidebar is expanded
            AnimatedVisibility(
                visible = isSidebarExpanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    label,
                    color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(start = 12.dp) // Added padding between icon and text
                )
            }
        }
    }
}
