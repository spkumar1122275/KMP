package com.kaushalvasava.apps.taskapp.viewmodel


import app.cash.sqldelight.db.SqlDriver
import com.kaushalvasava.apps.taskapp.TaskDatabase
import com.kaushalvasava.apps.taskapp.datasource.dao.*
import comkaushalvasavaappstaskapp.Department
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DepartmentViewModel(driver: SqlDriver) : ViewModel() {

    private val db = TaskDatabase(driver)

    val departments = MutableStateFlow<List<Department>>(emptyList())
    private var currentStoreId: Long? = null

    fun loadDepartments(storeId: Long? = null) {
        currentStoreId = storeId
        viewModelScope.launch(Dispatchers.IO) {
            departments.value = if (storeId == null)
                db.getDepartments()
            else
                db.getDepartmentsForStore(storeId)
        }
    }

    fun addDepartment(dept: Department) {
        viewModelScope.launch(Dispatchers.IO) {
            db.insertDepartment(dept)
            loadDepartments(currentStoreId)
        }
    }

    fun updateDepartment(dept: Department) {
        viewModelScope.launch(Dispatchers.IO) {
            db.updateDepartment(dept)
            loadDepartments(currentStoreId)
        }
    }

    fun deleteDepartment(deptId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteDepartment(deptId)
            loadDepartments(currentStoreId)
        }
    }
}
