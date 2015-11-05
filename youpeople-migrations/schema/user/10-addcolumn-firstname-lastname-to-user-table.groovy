databaseChangeLog {
    changeSet(id: 'addcolumn-firstname-lastname-to-user-table', author: 'mudryi_roman') {
        addColumn(tableName: 't_user') {
            column(name: 'first_name', type: 'VARCHAR(255)')
            column(name: 'last_name', type: 'VARCHAR(255)')
            column(name: 'phone', type: 'VARCHAR(255)')
        }
    }
}