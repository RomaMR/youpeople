databaseChangeLog {
    changeSet(id: '1438783344964-2', author: 'odisseus (generated)') {
        createTable(tableName: 't_organization') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'name', type: 'VARCHAR(255)') { constraints(nullable: false) }
        }
    }
}