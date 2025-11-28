package com.kaushalvasava.apps.taskapp.datasource.dao

import com.kaushalvasava.apps.taskapp.TaskDatabase
import comkaushalvasavaappstaskapp.Licenses

fun TaskDatabase.insertLicense(license: Licenses) {
    licensesQueries.insertLicense(
        license.license_id,
        license.store_id,
        license.license_name,
        license.license_ref_no,
        license.valid_till
    )
}

fun TaskDatabase.updateLicense(license: Licenses) {
    licensesQueries.updateLicense(
        license.store_id,
        license.license_name,
        license.license_ref_no,
        license.valid_till,
        license.license_id
    )
}

fun TaskDatabase.deleteLicense(id: Long) {
    licensesQueries.deleteLicense(id)
}

fun TaskDatabase.getLicenses(): List<Licenses> =
    licensesQueries.getLicenses().executeAsList()

fun TaskDatabase.getLicensesForStore(storeId: Long): List<Licenses> =
    licensesQueries.getLicensesForStore(storeId).executeAsList()
