databaseChangeLog {
    changeSet(id: 'revision_info', author: 'mudryi_roman') {
        createTable(tableName: 't_revision_info') {
            column(name: 'id', type: 'int4')
            column(name: 'timestamp', type: 'int8')
            column(name: 'audited_by', type: 'VARCHAR(255)')
        }
        addPrimaryKey(columnNames: 'id', constraintName: 'pk_revision_info', tableName: 't_revision_info')
    }
}