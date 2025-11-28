package com.kaushalvasava.apps.taskapp.viewmodel


import app.cash.sqldelight.db.SqlDriver
import com.kaushalvasava.apps.taskapp.TaskDatabase
import com.kaushalvasava.apps.taskapp.datasource.dao.*
import comkaushalvasavaappstaskapp.MainUser
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainUserViewModel(driver: SqlDriver) : ViewModel() {

    private val db = TaskDatabase(driver)

    val mainUsers = MutableStateFlow<List<MainUser>>(emptyList())

    fun loadUsers(storeId: Long? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            mainUsers.value = if (storeId == null)
                db.getMainUsers()
            else
                db.getMainUsersByStore(storeId)
        }
    }

    fun addUser(user: MainUser) {
        viewModelScope.launch(Dispatchers.IO) {
            db.insertMainUser(user)
            loadUsers(user.main_user_store_id)
        }
    }

    fun updateUser(user: MainUser) {
        viewModelScope.launch(Dispatchers.IO) {
            db.updateMainUser(user)
            loadUsers(user.main_user_store_id)
        }
    }

    fun deleteUser(id: Long, storeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteMainUser(id)
            loadUsers(storeId)
        }
    }
}
