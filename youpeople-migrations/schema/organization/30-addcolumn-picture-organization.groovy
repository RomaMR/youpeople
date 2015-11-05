databaseChangeLog {
    changeSet(id: 't_organization_picture', author: 'mudryi_roman') {
        addColumn(tableName: 't_organization') {
            column(name: 'picture', type: 'VARCHAR(255)')
        }
    }
}