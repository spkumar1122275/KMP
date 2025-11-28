package com.kaushalvasava.apps.taskapp.viewmodel


import app.cash.sqldelight.db.SqlDriver
import com.kaushalvasava.apps.taskapp.TaskDatabase
import com.kaushalvasava.apps.taskapp.datasource.dao.*
import comkaushalvasavaappstaskapp.Company
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CompanyViewModel(driver: SqlDriver) : ViewModel() {

    private val db = TaskDatabase(driver)

    val companies = MutableStateFlow<List<Company>>(emptyList())

    init {
        loadCompanies()
    }

    fun loadCompanies() {
        viewModelScope.launch(Dispatchers.IO) {
            companies.value = db.getCompanies()
        }
    }

    fun addCompany(company: Company) {
        viewModelScope.launch(Dispatchers.IO) {
            db.insertCompany(company)
            loadCompanies()
        }
    }

    fun updateCompany(company: Company) {
        viewModelScope.launch(Dispatchers.IO) {
            db.updateCompany(company)
            loadCompanies()
        }
    }

    fun deleteCompany(storeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteCompany(storeId)
            loadCompanies()
        }
    }
}
