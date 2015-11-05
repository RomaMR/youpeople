databaseChangeLog {
    changeSet(id: 't_search_log_aud', author: 'mudryi_roman') {
        createTable(tableName: 't_search_log_aud') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'rev', type: 'int4') { constraints(nullable: false) }
            column(name: 'revtype', type: 'smallint')
            column(name: 'query', type: 'VARCHAR(255)')
            column(name: 'search_timestamp', type: 'TIMESTAMP WITHOUT TIME ZONE')
            column(name: 'created_by', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'created_date', type: 'TIMESTAMP WITHOUT TIME ZONE') { constraints(nullable: false) }
            column(name: 'last_modified_by', type: 'VARCHAR(255)')
            column(name: 'last_modified_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
        }
        addPrimaryKey(columnNames: 'id,rev', constraintName: 'pk_search_log_aud', tableName: 't_search_log_aud')
    }
}