databaseChangeLog {
    changeSet(id: '1438783344964-11', author: 'odisseus') {
        createTable(tableName: 't_user') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'login', type: 'VARCHAR(255)'){ constraints(nullable: false) }
            column(name: 'password', type: 'VARCHAR(255)'){ constraints(nullable: false) }
        }
    }
}