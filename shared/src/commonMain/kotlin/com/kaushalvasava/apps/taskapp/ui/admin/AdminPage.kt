package com.kaushalvasava.apps.taskapp.ui.admin

sealed class AdminPage(val title: String) {
    object Companies : AdminPage("Companies")
    object Departments : AdminPage("Departments")
    object Licenses : AdminPage("Licenses")
    object MainUsers : AdminPage("Main Users")
    object TerminalUsers : AdminPage("Terminal Users")
    object AssignLicenses : AdminPage("Assign Licenses")
}