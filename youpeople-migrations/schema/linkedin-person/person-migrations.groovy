databaseChangeLog {
    changeSet(id: '1438783344964-3', author: 'odisseus (generated)') {
        createTable(tableName: 't_linkedin_person') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'first_name', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'last_name', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'work_email', type: 'VARCHAR(255)')
            column(name: 'personal_email', type: 'VARCHAR(255)')
        }
    }
}