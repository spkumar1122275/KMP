package com.kaushalvasava.apps.taskapp.ui.admin.mainuser

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import comkaushalvasavaappstaskapp.MainUser
import comkaushalvasavaappstaskapp.Company
import comkaushalvasavaappstaskapp.Department
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.ArrowDropDown


@Composable
fun AddEditMainUserDialog(
    initial: MainUser?,
    companies: List<Company>,
    departments: List<Department>,
    onCompanySelected: (Long) -> Unit,
    onDismiss: () -> Unit,
    onSave: (MainUser) -> Unit,
    isEditable: Boolean = true
) {
    var terminalId by remember { mutableStateOf(initial?.main_user_terminal_id ?: "") }
    var pan by remember { mutableStateOf(initial?.main_user_pan ?: "") }
    var password by remember { mutableStateOf(initial?.main_user_password ?: "") }
    var cellphone by remember { mutableStateOf(initial?.main_user_cellphone_number ?: "") }
    var firstName by remember { mutableStateOf(initial?.main_user_first_name ?: "") }
    var lastName by remember { mutableStateOf(initial?.main_user_last_name ?: "") }
    var empNo by remember { mutableStateOf(initial?.main_user_emp_no?.toString() ?: "") }

    var selectedCompany by remember {
        mutableStateOf(
            companies.firstOrNull { it.store_id == initial?.main_user_store_id }
                ?: companies.firstOrNull()
        )
    }
    var showCompanyMenu by remember { mutableStateOf(false) }

    var selectedDepartment by remember {
        mutableStateOf(
            departments.firstOrNull { it.dept_id == initial?.main_user_department_id }
        )
    }
    var showDeptMenu by remember { mutableStateOf(false) }

    val roles = listOf("main_user", "terminal_user")
    var selectedRole by remember { mutableStateOf(initial?.main_user_role ?: roles.first()) }
    var showRoleMenu by remember { mutableStateOf(false) }

    // --- VALIDATION ERROR STATES ---
    var errorCompany by remember { mutableStateOf(false) }
    var errorDept by remember { mutableStateOf(false) }
    var errorFirst by remember { mutableStateOf(false) }
    var errorLast by remember { mutableStateOf(false) }
    var errorPhone by remember { mutableStateOf(false) }
    var errorPassword by remember { mutableStateOf(false) }

    // --- SAVE CLICK HANDLER ---
    fun validateAndSave() {
        errorCompany = (selectedCompany == null)
        errorDept = (selectedDepartment == null)
        errorFirst = firstName.isBlank()
        errorLast = lastName.isBlank()
        errorPhone = cellphone.length < 10
        errorPassword = (initial == null && password.isBlank())

        if (errorCompany || errorDept || errorFirst || errorLast || errorPhone || errorPassword) {
            return
        }

        onSave(
            MainUser(
                mainUserId = initial?.mainUserId ?: 0,
                main_user_terminal_id = terminalId,
                main_user_pan = pan,
                main_user_store_id = selectedCompany?.store_id,
                main_user_department_id = selectedDepartment?.dept_id,
                main_user_password = password,
                main_user_cellphone_number = cellphone,
                main_user_first_name = firstName,
                main_user_last_name = lastName,
                main_user_role = selectedRole,
                main_user_emp_no = empNo.toLongOrNull(),
                main_user_is_admin = 1,
                main_user_is_active = 1
            )
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initial == null) "Add Main User" else "Edit Main User") },

        text = {
            Column(Modifier.fillMaxWidth()) {

                // COMPANY
                OutlinedTextField(
                    value = selectedCompany?.company_name ?: "Select Company",
                    onValueChange = {},
                    label = { Text("Company") },
                    isError = errorCompany,
                    enabled = isEditable,
                    readOnly = true,
                    trailingIcon = {
                        if (isEditable) IconButton({ showCompanyMenu = true }) {
                            Icon(Icons.Default.ArrowDropDown, null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorCompany) Text("Company required", color = MaterialTheme.colors.error)
                DropdownMenu(
                    expanded = showCompanyMenu,
                    onDismissRequest = { showCompanyMenu = false }
                ) {
                    companies.forEach { company ->
                        DropdownMenuItem({
                            selectedCompany = company
                            selectedDepartment = null
                            showCompanyMenu = false
                            onCompanySelected(company.store_id!!)
                        }) {
                            Text(company.company_name ?: "")
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // DEPARTMENT
                OutlinedTextField(
                    value = selectedDepartment?.dept_name ?: "Select Department",
                    onValueChange = {},
                    label = { Text("Department") },
                    isError = errorDept,
                    enabled = isEditable,
                    readOnly = true,
                    trailingIcon = {
                        if (isEditable) IconButton({ showDeptMenu = true }) {
                            Icon(Icons.Default.ArrowDropDown, null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorDept) Text("Department required", color = MaterialTheme.colors.error)

                DropdownMenu(
                    expanded = showDeptMenu,
                    onDismissRequest = { showDeptMenu = false }
                ) {
                    departments.forEach { dept ->
                        DropdownMenuItem({
                            selectedDepartment = dept
                            showDeptMenu = false
                        }) {
                            Text(dept.dept_name ?: "")
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // ROLE
                OutlinedTextField(
                    value = selectedRole,
                    onValueChange = {},
                    label = { Text("Role") },
                    enabled = isEditable,
                    readOnly = true,
                    trailingIcon = {
                        if (isEditable) IconButton({ showRoleMenu = true }) {
                            Icon(Icons.Default.ArrowDropDown, null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    expanded = showRoleMenu,
                    onDismissRequest = { showRoleMenu = false }
                ) {
                    roles.forEach { role ->
                        DropdownMenuItem({
                            selectedRole = role
                            showRoleMenu = false
                        }) {
                            Text(role)
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") },
                    enabled = isEditable,
                    isError = errorFirst,
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorFirst) Text("First name required", color = MaterialTheme.colors.error)

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    enabled = isEditable,
                    isError = errorLast,
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorLast) Text("Last name required", color = MaterialTheme.colors.error)

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = cellphone,
                    onValueChange = { cellphone = it },
                    label = { Text("Phone Number") },
                    enabled = isEditable,
                    isError = errorPhone,
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorPhone) Text("Phone number too short", color = MaterialTheme.colors.error)

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    enabled = isEditable,
                    isError = errorPassword,
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorPassword) Text("Password required", color = MaterialTheme.colors.error)
            }
        },

        confirmButton = {
            if (isEditable) {
                TextButton(onClick = { validateAndSave() }) { Text("Save") }
            }
        },

        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}