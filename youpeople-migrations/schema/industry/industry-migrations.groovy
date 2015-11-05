databaseChangeLog {
    changeSet(id: 't_industry', author: 'mudryi_roman') {
        createTable(tableName: 't_industry') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'name', type: 'VARCHAR(255)')
            column(name: 'created_by', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'created_date', type: 'TIMESTAMP WITHOUT TIME ZONE') { constraints(nullable: false) }
            column(name: 'last_modified_by', type: 'VARCHAR(255)')
            column(name: 'last_modified_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
        }
        addPrimaryKey(columnNames: 'id', constraintName: 'pk_industry', tableName: 't_industry')
    }
}