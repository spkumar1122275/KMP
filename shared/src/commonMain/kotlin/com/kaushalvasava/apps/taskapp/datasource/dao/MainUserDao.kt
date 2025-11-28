package com.kaushalvasava.apps.taskapp.datasource.dao

import com.kaushalvasava.apps.taskapp.TaskDatabase
import comkaushalvasavaappstaskapp.MainUser

fun TaskDatabase.insertMainUser(user: MainUser) {
    mainUserQueries.insertMainUser(
        user.main_user_terminal_id,
        user.main_user_pan,
        user.main_user_store_id,
        user.main_user_department_id,
        user.main_user_password,
        user.main_user_cellphone_number,
        user.main_user_first_name,
        user.main_user_last_name,
        user.main_user_role,
        user.main_user_emp_no,
        user.main_user_is_admin,
        user.main_user_is_active
    )
}

fun TaskDatabase.updateMainUser(user: MainUser) {
    mainUserQueries.updateMainUser(
        user.main_user_terminal_id,
        user.main_user_pan,
        user.main_user_store_id,
        user.main_user_department_id,
        user.main_user_password,
        user.main_user_cellphone_number,
        user.main_user_first_name,
        user.main_user_last_name,
        user.main_user_role,
        user.main_user_emp_no,
        user.main_user_is_admin,
        user.main_user_is_active,
        user.mainUserId
    )
}

fun TaskDatabase.deleteMainUser(id: Long) {
    mainUserQueries.deleteMainUser(id)
}

fun TaskDatabase.getMainUsers(): List<MainUser> =
    mainUserQueries.getMainUsers().executeAsList()

fun TaskDatabase.getMainUsersByStore(storeId: Long): List<MainUser> =
    mainUserQueries.getMainUsersByStore(storeId).executeAsList()
