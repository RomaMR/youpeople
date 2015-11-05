databaseChangeLog {
    changeSet(id: 'addcolumn-facebook-linkedin-to-user-organization-table', author: 'mudryi_roman') {
        addColumn(tableName: 't_user_organization') {
            column(name: 'linkedin', type: 'VARCHAR(255)')
            column(name: 'facebook', type: 'VARCHAR(255)')
        }
    }
}