package com.kaushalvasava.apps.taskapp.datasource.dao

import com.kaushalvasava.apps.taskapp.TaskDatabase
import comkaushalvasavaappstaskapp.Department

fun TaskDatabase.insertDepartment(dept: Department) {
    departmentQueries.insertDepartment(
        dept.dept_id,
        dept.dept_name,
        dept.dept_location,
        dept.deptStoreId
    )
}

fun TaskDatabase.updateDepartment(dept: Department) {
    departmentQueries.updateDepartment(
        dept.dept_name,
        dept.dept_location,
        dept.deptStoreId,
        dept.dept_id
    )
}

fun TaskDatabase.deleteDepartment(id: Long) {
    departmentQueries.deleteDepartment(id)
}

fun TaskDatabase.getDepartments(): List<Department> =
    departmentQueries.getDepartments().executeAsList()

fun TaskDatabase.getDepartmentById(id: Long): Department? =
    departmentQueries.getDepartmentById(id).executeAsOneOrNull()

fun TaskDatabase.getDepartmentsForStore(storeId: Long): List<Department> =
    departmentQueries.getDepartmentsForStore(storeId).executeAsList()
