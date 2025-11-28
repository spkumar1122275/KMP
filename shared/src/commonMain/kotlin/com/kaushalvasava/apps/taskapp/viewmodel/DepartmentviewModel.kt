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

    fun loadDepartments(storeId: Long? = null) {
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
            loadDepartments(dept.deptStoreId)
        }
    }

    fun updateDepartment(dept: Department) {
        viewModelScope.launch(Dispatchers.IO) {
            db.updateDepartment(dept)
            loadDepartments(dept.deptStoreId)
        }
    }

    fun deleteDepartment(id: Long, storeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteDepartment(id)
            loadDepartments(storeId)
        }
    }
}
