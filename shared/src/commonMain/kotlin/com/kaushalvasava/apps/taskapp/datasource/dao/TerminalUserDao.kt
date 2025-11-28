package com.kaushalvasava.apps.taskapp.datasource.dao

import com.kaushalvasava.apps.taskapp.TaskDatabase
import comkaushalvasavaappstaskapp.TerminalUsers

fun TaskDatabase.insertTerminalUser(user: TerminalUsers) {
    terminalUsersQueries.insertTerminalUser(
        user.terminal_user_terminal_id,
        user.terminal_user_pan,
        user.terminal_user_store_id,
        user.terminal_user_full_name,
        user.terminal_user_password,
        user.terminal_user_date,
        user.terminal_user_time,
        user.can_cancel_refund,
        user.can_collect_payment,
        user.can_view_cashiers,
        user.can_add_edit_cashiers,
        user.can_delete_cashiers,
        user.can_view_products,
        user.can_add_edit_products,
        user.can_delete_products,
        user.can_view_all_reports,
        user.can_save_send_reports,
        user.can_manage_pos,
        user.is_admin,
        user.cellphone_number,
        user.first_name,
        user.last_name,
        user.role,
        user.terminal_dept_id,
        user.emp_no
    )
}

fun TaskDatabase.updateTerminalUser(user: TerminalUsers) {
    terminalUsersQueries.updateTerminalUser(
        user.terminal_user_terminal_id,
        user.terminal_user_pan,
        user.terminal_user_store_id,
        user.terminal_user_full_name,
        user.terminal_user_password,
        user.terminal_user_date,
        user.terminal_user_time,
        user.can_cancel_refund,
        user.can_collect_payment,
        user.can_view_cashiers,
        user.can_add_edit_cashiers,
        user.can_delete_cashiers,
        user.can_view_products,
        user.can_add_edit_products,
        user.can_delete_products,
        user.can_view_all_reports,
        user.can_save_send_reports,
        user.can_manage_pos,
        user.is_admin,
        user.cellphone_number,
        user.first_name,
        user.last_name,
        user.role,
        user.terminal_dept_id,
        user.emp_no,
        user.terminalUserId
    )
}

fun TaskDatabase.deleteTerminalUser(id: Long) {
    terminalUsersQueries.deleteTerminalUser(id)
}

fun TaskDatabase.getTerminalUsers(): List<TerminalUsers> =
    terminalUsersQueries.getTerminalUsers().executeAsList()

fun TaskDatabase.getTerminalUsersByStore(storeId: Long): List<TerminalUsers> =
    terminalUsersQueries.getTerminalUsersByStore(storeId).executeAsList()
