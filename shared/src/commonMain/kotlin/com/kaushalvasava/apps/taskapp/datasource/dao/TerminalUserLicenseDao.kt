package com.kaushalvasava.apps.taskapp.datasource.dao

import com.kaushalvasava.apps.taskapp.TaskDatabase
import comkaushalvasavaappstaskapp.TerminalUserLicense
import comkaushalvasavaappstaskapp.Licenses

fun TaskDatabase.addLicenseToUser(license: TerminalUserLicense) {
    terminalUserLicenseQueries.insertTerminalUserLicense(
        license.terminalUserId,
        license.licenseId,
        license.licenseName
    )
}

fun TaskDatabase.removeLicenseFromUser(userId: Long, licenseId: Long) {
    terminalUserLicenseQueries.deleteTerminalUserLicense(
        userId,
        licenseId
    )
}

fun TaskDatabase.getLicensesForUser(userId: Long): List<TerminalUserLicense> =
    terminalUserLicenseQueries.getLicensesForUser(userId).executeAsList()

fun TaskDatabase.getUserLicensesFull(userId: Long): List<Licenses> =
    terminalUserLicenseQueries.getUserLicensesFull(userId).executeAsList()
