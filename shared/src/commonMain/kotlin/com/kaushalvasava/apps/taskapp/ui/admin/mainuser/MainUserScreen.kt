package com.kaushalvasava.apps.taskapp.ui.admin.mainuser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaushalvasava.apps.taskapp.viewmodel.CompanyViewModel
import com.kaushalvasava.apps.taskapp.viewmodel.DepartmentViewModel
import com.kaushalvasava.apps.taskapp.viewmodel.MainUserViewModel
import comkaushalvasavaappstaskapp.MainUser

@Composable
fun MainUserScreen(
    mainUserViewModel: MainUserViewModel,
    companyViewModel: CompanyViewModel,
    departmentViewModel: DepartmentViewModel,
    currentUserRole: String        // <-- admin, main_user, terminal_user
) {
    val users by mainUserViewModel.mainUsers.collectAsState()
    val companies by companyViewModel.companies.collectAsState()
    val departments by departmentViewModel.departments.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var userToEdit by remember { mutableStateOf<MainUser?>(null) }

    val canAdd = currentUserRole == "admin" || currentUserRole == "main_user"
    val canDelete = currentUserRole == "admin"
    val canEdit = currentUserRole != "terminal_user"

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Main Users", style = MaterialTheme.typography.h5)

            if (canAdd) {
                Button(onClick = {
                    userToEdit = null
                    showDialog = true
                }) { Text("Add User") }
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(users) { user ->
                UserItem(
                    mainUser = user,
                    onEdit = if (canEdit) {
                        {
                            userToEdit = user
                            departmentViewModel.loadDepartments(user.main_user_store_id)
                            showDialog = true
                        }
                    } else null,
                    onDelete = if (canDelete) {
                        {
                            mainUserViewModel.deleteUser(
                                id = user.mainUserId,
                                storeId = user.main_user_store_id ?: 0L
                            )
                        }
                    } else null
                )
            }
        }
    }

    if (showDialog) {
        AddEditMainUserDialog(
            initial = userToEdit,
            companies = companies,
            departments = departments,
            onCompanySelected = { departmentViewModel.loadDepartments(it) },
            onDismiss = { showDialog = false },
            onSave = {
                if (userToEdit == null) mainUserViewModel.addUser(it)
                else mainUserViewModel.updateUser(it)
                showDialog = false
            },
            isEditable = canEdit       // <-- disable all fields for terminal_user
        )
    }
}