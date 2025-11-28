package com.kaushalvasava.apps.taskapp.datasource.dao

import com.kaushalvasava.apps.taskapp.TaskDatabase
import comkaushalvasavaappstaskapp.Company

fun TaskDatabase.insertCompany(company: Company) {
    companyQueries.insertCompany(
        company.store_id,
        company.company_id,
        company.company_name,
        company.address,
        company.phone,
        company.tax_id
    )
}

fun TaskDatabase.updateCompany(company: Company) {
    companyQueries.updateCompany(
        company.company_id,
        company.company_name,
        company.address,
        company.phone,
        company.tax_id,
        company.store_id
    )
}

fun TaskDatabase.deleteCompany(storeId: Long) {
    companyQueries.deleteCompany(storeId)
}

fun TaskDatabase.getCompanies(): List<Company> =
    companyQueries.getCompanies().executeAsList()

fun TaskDatabase.getCompanyById(storeId: Long): Company? =
    companyQueries.getCompanyById(storeId).executeAsOneOrNull()
