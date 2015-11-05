databaseChangeLog {
    changeSet(id: '1438783344964-4', author: 'odisseus (generated)') {
        createTable(tableName: 't_search') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'query', type: 'VARCHAR(255)')
            column(name: 'search_timestamp', type: 'TIMESTAMP WITHOUT TIME ZONE')
        }
    }
}