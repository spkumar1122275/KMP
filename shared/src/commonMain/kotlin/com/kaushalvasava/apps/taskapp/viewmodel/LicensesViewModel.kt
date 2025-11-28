package com.kaushalvasava.apps.taskapp.viewmodel

import app.cash.sqldelight.db.SqlDriver
import com.kaushalvasava.apps.taskapp.TaskDatabase
import com.kaushalvasava.apps.taskapp.datasource.dao.*
import comkaushalvasavaappstaskapp.Licenses
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LicensesViewModel(driver: SqlDriver) : ViewModel() {

    private val db = TaskDatabase(driver)

    val licenses = MutableStateFlow<List<Licenses>>(emptyList())

    fun loadLicenses(storeId: Long? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            licenses.value = if (storeId == null)
                db.getLicenses()
            else
                db.getLicensesForStore(storeId)
        }
    }

    fun addLicense(license: Licenses) {
        viewModelScope.launch(Dispatchers.IO) {
            db.insertLicense(license)
            loadLicenses(license.store_id)
        }
    }

    fun updateLicense(license: Licenses) {
        viewModelScope.launch(Dispatchers.IO) {
            db.updateLicense(license)
            loadLicenses(license.store_id)
        }
    }

    fun deleteLicense(id: Long, storeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteLicense(id)
            loadLicenses(storeId)
        }
    }
}
