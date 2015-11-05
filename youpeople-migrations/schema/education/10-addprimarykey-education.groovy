databaseChangeLog {
    changeSet(id: '20150814-2', author: 'odisseus') {
        addPrimaryKey(columnNames: 'id', constraintName: 'pk_education', tableName: 't_education')
    }
}