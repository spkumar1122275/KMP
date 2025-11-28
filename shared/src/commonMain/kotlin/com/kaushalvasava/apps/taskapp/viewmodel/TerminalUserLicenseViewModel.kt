package com.kaushalvasava.apps.taskapp.viewmodel


import app.cash.sqldelight.db.SqlDriver
import com.kaushalvasava.apps.taskapp.TaskDatabase
import com.kaushalvasava.apps.taskapp.datasource.dao.*
import comkaushalvasavaappstaskapp.TerminalUserLicense
import comkaushalvasavaappstaskapp.Licenses
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TerminalUserLicenseViewModel(driver: SqlDriver) : ViewModel() {

    private val db = TaskDatabase(driver)

    val licensesForUser = MutableStateFlow<List<TerminalUserLicense>>(emptyList())
    val licenseDetails = MutableStateFlow<List<Licenses>>(emptyList())

    fun loadUserLicenses(userId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            licensesForUser.value = db.getLicensesForUser(userId)
            licenseDetails.value = db.getUserLicensesFull(userId)
        }
    }

    fun addLicenseToUser(rel: TerminalUserLicense) {
        viewModelScope.launch(Dispatchers.IO) {
            db.addLicenseToUser(rel)
            loadUserLicenses(rel.terminalUserId)
        }
    }

    fun removeLicenseFromUser(userId: Long, licenseId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            db.removeLicenseFromUser(userId, licenseId)
            loadUserLicenses(userId)
        }
    }
}
