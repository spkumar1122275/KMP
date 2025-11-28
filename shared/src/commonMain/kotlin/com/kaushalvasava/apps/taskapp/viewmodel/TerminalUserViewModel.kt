package com.kaushalvasava.apps.taskapp.viewmodel


import app.cash.sqldelight.db.SqlDriver
import com.kaushalvasava.apps.taskapp.TaskDatabase
import com.kaushalvasava.apps.taskapp.datasource.dao.*
import comkaushalvasavaappstaskapp.TerminalUsers
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TerminalUsersViewModel(driver: SqlDriver) : ViewModel() {

    private val db = TaskDatabase(driver)

    val terminalUsers = MutableStateFlow<List<TerminalUsers>>(emptyList())

    fun loadTerminalUsers(storeId: Long? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            terminalUsers.value = if (storeId == null)
                db.getTerminalUsers()
            else
                db.getTerminalUsersByStore(storeId)
        }
    }

    fun addTerminalUser(user: TerminalUsers) {
        viewModelScope.launch(Dispatchers.IO) {
            db.insertTerminalUser(user)
            loadTerminalUsers(user.terminal_user_store_id)
        }
    }

    fun updateTerminalUser(user: TerminalUsers) {
        viewModelScope.launch(Dispatchers.IO) {
            db.updateTerminalUser(user)
            loadTerminalUsers(user.terminal_user_store_id)
        }
    }

    fun deleteTerminalUser(id: Long, storeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteTerminalUser(id)
            loadTerminalUsers(storeId)
        }
    }
}
