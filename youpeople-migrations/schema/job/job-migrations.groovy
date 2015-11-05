databaseChangeLog {
    changeSet(id: '1438783344964-1', author: 'odisseus (generated)') {
        createTable(tableName: 't_job') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'person_id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'organization_id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'position', type: 'VARCHAR(255)')
            column(name: 'start_date', type: 'date')
            column(name: 'end_date', type: 'date')
        }
    }
}