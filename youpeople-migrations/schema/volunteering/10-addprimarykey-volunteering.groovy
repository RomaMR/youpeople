databaseChangeLog {
    changeSet(id: '20150814-4', author: 'odisseus') {
        addPrimaryKey(columnNames: 'id', constraintName: 'pk_volunteering', tableName: 't_volunteering')
    }
}